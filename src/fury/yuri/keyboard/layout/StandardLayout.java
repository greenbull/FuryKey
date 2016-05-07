package fury.yuri.keyboard.layout;

import java.io.File;
import java.util.List;
import java.util.Map;

public class StandardLayout implements ILayout {

	@Override
	public double getDistance(int pos1, int pos2) {

		if (pos1 < 1 || pos1 > 26 || pos2 < 1 || pos2 > 26) {
			throw new IllegalArgumentException(
					"There is no such positions in Standard Layout: (" + pos1 + ", " + pos2 + ")");
		}

		int row1, column1, row2, column2;

		if (pos1 <= 9) {
			row1 = 1;
			column1 = pos1;
		} else if (pos1 <= 17) {
			row1 = 2;
			column1 = pos1 - 9;
		} else {
			row1 = 3;
			column1 = pos1 - 17;
		}

		if (pos2 <= 9) {
			row2 = 1;
			column2 = pos2;
		} else if (pos2 <= 17) {
			row2 = 2;
			column2 = pos2 - 9;
		} else {
			row2 = 3;
			column2 = pos2 - 17;
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
		for(int i=0; i<9; i++) {
			sb.append("+---");
		}
		sb.append("+").append("\n");
		for(int i=1; i<=9; i++) {
			sb.append("| ").append(positionKeyMap.get(i)).append(" ");
		}
		sb.append("|").append("\n");
		for(int i=0; i<9; i++) {
			sb.append("+---");
		}
		sb.append("+").append("\n");
		for(int i=10; i<=17; i++) {
			sb.append("| ").append(positionKeyMap.get(i)).append(" ");
		}
		sb.append("|").append("\n");
		for(int i=0; i<9; i++) {
			sb.append("+---");
		}
		sb.append("+").append("\n");
		for(int i=18; i<=26; i++) {
			sb.append("| ").append(positionKeyMap.get(i)).append(" ");
		}
		sb.append("|").append("\n");
		for(int i=0; i<9; i++) {
			sb.append("+---");
		}
		sb.append("+").append("\n");
		
		return sb.toString();
	}
}
