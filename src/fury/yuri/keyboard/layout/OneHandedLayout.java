package fury.yuri.keyboard.layout;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Map;

public class OneHandedLayout implements ILayout {
	
	private String name;
	private int numberOfKeys;
	private int[][] positions;
	private String language;
	
	public OneHandedLayout(String name, File layoutFile) {
		
		this.name = name;
		this.numberOfKeys = 0;
		parseFile(layoutFile);
		if(numberOfKeys == 26) {
			language = "EN";
		} else {
			language = "HR";
		}
	}
	
	public String getName() {
		return name;
	}

	private void parseFile(File layoutFile) {
		
		try (BufferedReader br = new BufferedReader(new FileReader(layoutFile))) {
		    String line;
		    boolean firstLine = true;
		    int currPos = 1;
		    int currRow = 0;
		    int currCol = 0;
		    while ((line = br.readLine()) != null) {
		    	if(firstLine) {
		    		String[] rowCol = line.split("x");
		    		int rows = Integer.parseInt(rowCol[0]);
		    		int cols = Integer.parseInt(rowCol[1]);
		    		positions = new int[rows][cols];
		    		firstLine = false;
		    	} else {
		    		String[] split = line.split("");
		    		for(String s : split) {
		    			if(s.equals("K")) {
		    				numberOfKeys++;
		    				positions[currRow][currCol] = currPos;
		    				currPos++;
		    				currCol++;
		    			} else if(s.equals(" ")) {
		    				positions[currRow][currCol] = -1;
		    				currCol++;
		    			}
		    		}
		    		currRow++;
		    		currCol = 0;
		    	}
		    }
		} catch(Exception e) {}
	}

	@Override
	public double getDistance(int pos1, int pos2) {
		
		int row1=0, row2=0, column1=0, column2=0;
		
		for(int i=0; i<positions.length; i++) {
			for(int j=0; j<positions[0].length; j++) {
				if(positions[i][j] == pos1) {
					row1 = i+1;
					column1 = j+1;
				}
				if(positions[i][j] == pos2) {
					row2 = i+1;
					column2 = j+1;
				}
			}
		}
		
		return Math.sqrt((row1 - row2) * (row1 - row2) + (column1 - column2) * (column1 - column2));
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
	
	@Override
	public String language() {
		
		return language;
	}
}
