package fury.yuri.keyboard;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class KeysHR implements IKeys {

	private static KeysHR keysHR = new KeysHR();

	private char[] letters = new char[31];
	private double[][] matrix = new double[31][31];

	private KeysHR() {
		fillLetters();
		File bigrams = new File("lib/bigramsHR.txt");
		try (BufferedReader br = new BufferedReader(new FileReader(bigrams))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	String[] split = line.split(";");
		    	double value = Double.parseDouble(split[2].replaceAll(",", "."));
		    	char letter1 = split[0].charAt(0);
		    	char letter2 = split[0].charAt(1);
		    	int x = -1;
		    	int y = -1;
		    	for(int i=0; i<31; i++) {
		    		if(letter1 == letters[i]) {
		    			x = i;
		    		}
		    		if(letter2 == letters[i]) {
		    			y = i;
		    		}
		    	}
		    	if(!(x == -1 || y == -1)) {
		    		matrix[x][y] = value;
		    	}
		    }
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void fillLetters() {
		
		letters[0] = 'a';
		letters[1] = 'b';
		letters[2] = 'c';
		letters[3] = 'č';
		letters[4] = 'ć';
		letters[5] = 'd';
		letters[6] = 'đ';
		letters[7] = 'e';
		letters[8] = 'f';
		letters[9] = 'g';
		letters[10] = 'h';
		letters[11] = 'i';
		letters[12] = 'j';
		letters[13] = 'k';
		letters[14] = 'l';
		letters[15] = 'm';
		letters[16] = 'n';
		letters[17] = 'o';
		letters[18] = 'p';
		letters[19] = 'r';
		letters[20] = 's';
		letters[21] = 'š';
		letters[22] = 't';
		letters[23] = 'u';
		letters[24] = 'v';
		letters[25] = 'z';
		letters[26] = 'ž';
		letters[27] = 'q';
		letters[28] = 'w';
		letters[29] = 'x';
		letters[30] = 'y';
	}

	public static KeysHR getInstance() {

		return keysHR;
	}

	public double getProbabilityFor(char key1, char key2) {

		int i = -1;
		int j = -1;

		for (int c = 0; c < letters.length; c++) {
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
