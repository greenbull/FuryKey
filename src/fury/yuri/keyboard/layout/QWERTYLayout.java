package fury.yuri.keyboard.layout;

import java.io.File;
import java.util.List;
import java.util.Map;

public class QWERTYLayout implements ILayout {

	@Override
	public double getDistance(int pos1, int pos2) {
		
		if (pos1 < 1 || pos1 > 26 || pos2 < 1 || pos2 > 26) {
			throw new IllegalArgumentException(
					"There is no such positions in Standard Layout: (" + pos1 + ", " + pos2 + ")");
		}

		int row1, column1, row2, column2;

		if (pos1 <= 10) {
			row1 = 1;
			column1 = pos1;
		} else if (pos1 <= 19) {
			row1 = 2;
			column1 = pos1 - 10;
		} else {
			row1 = 3;
			column1 = pos1 - 19;
		}

		if (pos2 <= 10) {
			row2 = 1;
			column2 = pos2;
		} else if (pos2 <= 19) {
			row2 = 2;
			column2 = pos2 - 10;
		} else {
			row2 = 3;
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
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String language() {
		// TODO Auto-generated method stub
		return "EN";
	}
}
