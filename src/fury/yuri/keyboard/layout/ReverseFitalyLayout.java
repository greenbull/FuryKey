package fury.yuri.keyboard.layout;

import java.io.File;
import java.util.Map;

public class ReverseFitalyLayout implements ILayout {
	
	//2-handed, pa na taj način gleda udaljenost

	@Override
	public double getDistance(int pos1, int pos2) {
		
		if(pos1 <= 13 && pos2 <= 13) {
			int row1, row2, column1, column2;
			if(pos1 <= 3) {
				row1 = 1;
				column1 = pos1;
			} else if(pos1 <= 6) {
				row1 = 2;
				column1 = pos1 - 3;
			} else if(pos1 <= 9) {
				row1 = 3;
				column1 = pos1 - 6;
			} else {
				row1 = 4;
				column1 = pos1 - 9;
			}

			if(pos2 <= 3) {
				row2 = 1;
				column2 = pos2;
			} else if(pos2 <= 6) {
				row2 = 2;
				column2 = pos2 - 3;
			} else if(pos2 <= 9) {
				row2 = 3;
				column2 = pos2 - 6;
			} else {
				row2 = 4;
				column2 = pos2 - 9;
			}
			return Math.sqrt((row1 - row2) * (row1 - row2) + (column1 - column2) * (column1 - column2));
		} else if(pos1 > 13 && pos2 > 13) {
			int row1, row2, column1, column2;
			if(pos1 <= 16) {
				row1 = 1;
				column1 = pos1 - 13 + 5;
			} else if(pos1 <= 19) {
				row1 = 2;
				column1 = pos1 - 16 + 5;
			} else if(pos1 <= 22) {
				row1 = 3;
				column1 = pos1 - 19 + 5;
			} else {
				row1 = 4;
				if(pos1 == 26) {
					column1 = 5;
				} else {
					column1 = pos1 - 22 + 5;
				}
			}

			if(pos2 <= 16) {
				row2 = 1;
				column2 = pos2 - 13 + 5;
			} else if(pos2 <= 19) {
				row2 = 2;
				column2 = pos2 - 16 + 5;
			} else if(pos2 <= 22) {
				row2 = 3;
				column2 = pos2 - 19 + 5;
			} else {
				row2 = 4;
				if(pos2 == 26) {
					column2 = 5;
				} else {
					column2 = pos2 - 22 + 5;
				}
			}
			return Math.sqrt((row1 - row2) * (row1 - row2) + (column1 - column2) * (column1 - column2));
		} else {
			if(pos1 <= 13 && pos2 > 13) {
				return getDistance(8, pos1) + getDistance(21, pos2);
			} else {
				return getDistance(8, pos2) + getDistance(21, pos1);
			}
		}
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
		sb.append("+---+---+---+       +---+---+---+").append("\n");
		for(int i=1; i<=3; i++) {
			sb.append("| ").append(positionKeyMap.get(i)).append(" ");
		}
		sb.append("|");
		sb.append("       ");
		for(int i=14; i<=16; i++) {
			sb.append("| ").append(positionKeyMap.get(i)).append(" ");
		}
		sb.append("|").append("\n");
		
		sb.append("+---+---+---+       +---+---+---+").append("\n");
		for(int i=4; i<=6; i++) {
			sb.append("| ").append(positionKeyMap.get(i)).append(" ");
		}
		sb.append("|");
		sb.append("       ");
		for(int i=17; i<=19; i++) {
			sb.append("| ").append(positionKeyMap.get(i)).append(" ");
		}
		sb.append("|").append("\n");
		
		sb.append("+---+---+---+       +---+---+---+").append("\n");
		for(int i=7; i<=9; i++) {
			sb.append("| ").append(positionKeyMap.get(i)).append(" ");
		}
		sb.append("|");
		sb.append("       ");
		for(int i=20; i<=22; i++) {
			sb.append("| ").append(positionKeyMap.get(i)).append(" ");
		}
		sb.append("|").append("\n");
		
		sb.append("+---+---+---+---+---+---+---+---+").append("\n");
		for(int i=10; i<=12; i++) {
			sb.append("| ").append(positionKeyMap.get(i)).append(" ");
		}
		sb.append("| ").append(positionKeyMap.get(13)).append(" ");
		sb.append("| ").append(positionKeyMap.get(26)).append(" ");
		for(int i=23; i<=25; i++) {
			sb.append("| ").append(positionKeyMap.get(i)).append(" ");
		}
		sb.append("|").append("\n");
		sb.append("+---+---+---+---+---+---+---+---+").append("\n");
		
		return sb.toString();
	}
	
	@Override
	public String language() {
		// TODO Auto-generated method stub
		return "EN";
	}
}
