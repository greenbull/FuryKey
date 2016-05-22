package fury.yuri.keyboard.layout;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TwoHandedLayout implements ILayout {

	private String name;
	private int numberOfKeys;
	private int[][] positions;
	private int leftCenterRow;
	private int leftCenterCol;
	private int rightCenterRow;
	private int rightCenterCol;
	private List<Integer> leftPositions;
	private List<Integer> rightPositions;
	private String language;

	public TwoHandedLayout(String name, File layoutFile) {
		leftPositions = new ArrayList<>();
		rightPositions = new ArrayList<>();
		this.name = name;
		this.numberOfKeys = 0;
		parseFile(layoutFile);
		if (numberOfKeys == 26) {
			language = "EN";
		} else if (numberOfKeys == 30) {
			language = "HR";
		}
	}

	private void parseFile(File layoutFile) {

		try (BufferedReader br = new BufferedReader(new FileReader(layoutFile))) {
			String line;
			boolean firstLine = true;
			int currPos = 1;
			int currRow = 0;
			int currCol = 0;
			while ((line = br.readLine()) != null) {
				if (firstLine) {
					String[] rowCol = line.split("x");
					int rows = Integer.parseInt(rowCol[0]);
					int cols = Integer.parseInt(rowCol[1]);
					positions = new int[rows][cols];
					firstLine = false;
				} else {
					String[] split = line.split("");
					for (String s : split) {
						if (s.equals("L")) {
							numberOfKeys++;
							leftPositions.add(currPos);
							positions[currRow][currCol] = currPos;
							currPos++;
							currCol++;
						} else if (s.equals(" ")) {
							positions[currRow][currCol] = -1;
							currCol++;
						} else if (s.equals("R")) {
							numberOfKeys++;
							rightPositions.add(currPos);
							positions[currRow][currCol] = currPos;
							currPos++;
							currCol++;
						} else if (s.equals("O")) {
							numberOfKeys++;
							positions[currRow][currCol] = currPos;
							leftPositions.add(currPos);
							leftCenterRow = currRow+1;
							leftCenterCol = currCol+1;
							currPos++;
							currCol++;
						} else if (s.equals("X")) {
							numberOfKeys++;
							positions[currRow][currCol] = currPos;
							rightPositions.add(currPos);
							rightCenterRow = currRow+1;
							rightCenterCol = currCol+1;
							currPos++;
							currCol++;
						}
					}
					currRow++;
					currCol = 0;
				}
			}
		} catch (Exception e) {
		}
	}

	@Override
	public String language() {

		return language;
	}

	@Override
	public double getDistance(int pos1, int pos2) {

		int row1 = 0, row2 = 0, column1 = 0, column2 = 0;

		for (int i = 0; i < positions.length; i++) {
			for (int j = 0; j < positions[0].length; j++) {
				if (positions[i][j] == pos1) {
					row1 = i + 1;
					column1 = j + 1;
				}
				if (positions[i][j] == pos2) {
					row2 = i + 1;
					column2 = j + 1;
				}
			}
		}

		if ((leftPositions.contains(pos1) && leftPositions.contains(pos2))
				|| (rightPositions.contains(pos1) && rightPositions.contains(pos2))) {
			return Math.sqrt((row1 - row2) * (row1 - row2) + (column1 - column2) * (column1 - column2));
		} else if (leftPositions.contains(pos1) && rightPositions.contains(pos2)) {
			double leftDist = Math.sqrt((row1 - leftCenterRow) * (row1 - leftCenterRow)
					+ (column1 - leftCenterCol) * (column1 - leftCenterCol));
			double rightDist = Math.sqrt((rightCenterRow - row2) * (rightCenterRow - row2)
					+ (rightCenterCol - column2) * (rightCenterCol - column2));
			return leftDist+rightDist;
		} else if(leftPositions.contains(pos2) && rightPositions.contains(pos1)){
			double leftDist = Math.sqrt((row2 - leftCenterRow) * (row2 - leftCenterRow)
					+ (column2 - leftCenterCol) * (column2 - leftCenterCol));
			double rightDist = Math.sqrt((rightCenterRow - row1) * (rightCenterRow - row1)
					+ (rightCenterCol - column1) * (rightCenterCol - column1));
			return leftDist+rightDist;
		} else {
			return 0.0;
		}
	}

	@Override
	public int numberOfKeys() {

		return numberOfKeys;
	}

	@Override
	public void toFile(File file) {
		// TODO Auto-generated method stub

	}

	@Override
	public String keyboardToString(Map<Integer, Character> positionKeyMap) {
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<positions.length; i++) {
			
			for(int j=0; j<positions[i].length; j++) {
				sb.append("+---");
			}
			sb.append("+").append("\n");
			for(int j=0; j<positions[i].length; j++) {
				String var = " ";
				if(positionKeyMap.get(positions[i][j]) != null) {
					var = positionKeyMap.get(positions[i][j]).toString();
				}
				sb.append("| ").append(var).append(" ");
			}
			sb.append("|").append("\n");
		}
		for(int j=0; j<positions[0].length; j++) {
			sb.append("+---");
		}
		sb.append("+").append("\n");
		
		return sb.toString();
	}
}
