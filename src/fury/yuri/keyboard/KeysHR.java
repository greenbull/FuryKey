package fury.yuri.keyboard;

public class KeysHR implements IKeys {

	private static KeysHR keysHR = new KeysHR();

	private char[] letters;
	private double[][] matrix;

	private KeysHR() {
	}

	public static KeysHR getInstance() {

		return keysHR;
	}

	public double getProbabilityFor(char key1, char key2) {

		int i = -1;
		int j = -1;

		for (int c = 0; c < letters.length; i++) {
			if (letters[c] == key1) {
				i = c;
			}
			if (letters[c] == key2) {
				j = c;
			}
		}

		if (i == -1 || j == -1) {
			throw new IllegalArgumentException("There is no such keys in Croatian alphabet: (" + key1 + ", " + key2 + ")");
		}

		return matrix[i][j];
	}

	@Override
	public char[] getKeys() {

		return letters;
	}

	@Override
	public int numberOfKeys() {

		return letters.length;
	}
}
