package fury.yuri.keyboard.layout;

import java.io.File;
import java.util.Map;

public class Rows4Layout implements ILayout {

	@Override
	public double getDistance(int pos1, int pos2) {
		
		if (pos1 < 1 || pos1 > 26 || pos2 < 1 || pos2 > 26) {
			throw new IllegalArgumentException(
					"There is no such positions in Standard Layout: (" + pos1 + ", " + pos2 + ")");
		}

		int row1, column1, row2, column2;

		if (pos1 <= 6) {
			row1 = 1;
			column1 = pos1;
		} else if (pos1 <= 13) {
			row1 = 2;
			column1 = pos1 - 6;
		} else if(pos1 <= 19){
			row1 = 3;
			column1 = pos1 - 13;
		} else {
			row1 = 4;
			column1 = pos1 - 19;
		}

		if (pos2 <= 6) {
			row2 = 1;
			column2 = pos2;
		} else if (pos2 <= 13) {
			row2 = 2;
			column2 = pos2 - 6;
		} else if(pos2 <= 19){
			row2 = 3;
			column2 = pos2 - 13;
		} else {
			row2 = 4;
			column2 = pos2 - 19;
		}

		return Math.sqrt((row1 - row2) * (row1 - row2) + (column1 - column2) * (column1 - column2));
	}

	@Override
	public int numberOfKeys() {
		
		return 26;
	}

	@Override
	public void toFile(File file) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String keyboardToString(Map<Integer, Character> positionKeyMap) {
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<6; i++) {
			sb.append("+---");
		}
		sb.append("+").append("\n");
		for(int i=1; i<=6; i++) {
			sb.append("| ").append(positionKeyMap.get(i)).append(" ");
		}
		sb.append("|").append("\n");
		for(int i=0; i<7; i++) {
			sb.append("+---");
		}
		sb.append("+").append("\n");
		for(int i=7; i<=13; i++) {
			sb.append("| ").append(positionKeyMap.get(i)).append(" ");
		}
		sb.append("|").append("\n");
		for(int i=0; i<7; i++) {
			sb.append("+---");
		}
		sb.append("+").append("\n");
		for(int i=14; i<=19; i++) {
			sb.append("| ").append(positionKeyMap.get(i)).append(" ");
		}
		sb.append("|").append("\n");
		for(int i=0; i<7; i++) {
			sb.append("+---");
		}
		sb.append("+").append("\n");
		for(int i=20; i<=26; i++) {
			sb.append("| ").append(positionKeyMap.get(i)).append(" ");
		}
		sb.append("|").append("\n");
		for(int i=0; i<7; i++) {
			sb.append("+---");
		}
		sb.append("+").append("\n");
		
		return sb.toString();
	}
	
	@Override
	public String language() {
		// TODO Auto-generated method stub
		return "EN";
	}
}
