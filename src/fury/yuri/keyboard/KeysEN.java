package fury.yuri.keyboard;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

//["he",86697336727],

public class KeysEN implements IKeys {
	
	private static KeysEN keysEN = new KeysEN();
	
	private char[] letters = new char[26];
	private double[][] matrix = new double[26][26];
	
	private KeysEN() {
		fillLetters();
		double[][] tempMatrix = new double[26][26];
		File bigrams = new File("lib/bigramsEN.txt");
		try (BufferedReader br = new BufferedReader(new FileReader(bigrams))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       String[] split = line.split(",");
		       String bigram = split[0].split("\"")[1];
		       char letter1 = bigram.charAt(0);
		       char letter2 = bigram.charAt(1);
		       String numStr = split[1].split("]")[0];
		       double freq = Double.parseDouble(numStr);
		       int i1 = 0;
		       int i2 = 0;
		       for(int i=0; i<26; i++) {
		    	   if(letter1 == letters[i]) {
		    		   i1 = i;
		    	   }
		    	   if(letter2 == letters[i]) {
		    		   i2 = i;
		    	   }
		       }
		       tempMatrix[i1][i2] = freq;
		    }
		} catch(Exception e) {
			e.printStackTrace();
		}
		adjustMatrix(tempMatrix);
	}
	
	private void adjustMatrix(double[][] tempMatrix) {
		
		double sum = 0;
		for(int i=0; i<26; i++) {
			for(int j=0; j<26; j++) {
				sum += tempMatrix[i][j];
			}
		}
		
		for(int i=0; i<26; i++) {
			for(int j=0; j<26; j++) {
				double val = tempMatrix[i][j] / sum;
				matrix[i][j] = val;
			}
		}
	}

	private void fillLetters() {
		
		letters[0] = 'a';
		letters[1] = 'b';
		letters[2] = 'c';
		letters[3] = 'd';
		letters[4] = 'e';
		letters[5] = 'f';
		letters[6] = 'g';
		letters[7] = 'h';
		letters[8] = 'i';
		letters[9] = 'j';
		letters[10] = 'k';
		letters[11] = 'l';
		letters[12] = 'm';
		letters[13] = 'n';
		letters[14] = 'o';
		letters[15] = 'p';
		letters[16] = 'q';
		letters[17] = 'r';
		letters[18] = 's';
		letters[19] = 't';
		letters[20] = 'u';
		letters[21] = 'v';
		letters[22] = 'w';
		letters[23] = 'x';
		letters[24] = 'y';
		letters[25] = 'z';
	}

	public static KeysEN getInstance() {
		return keysEN;
	}

	@Override
	public char[] getKeys() {
		
		return letters;
	}

	@Override
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
			throw new IllegalArgumentException("There is no such keys in English alphabet: (" + key1 + ", " + key2 + ")");
		}

		return matrix[i][j];
	}

	@Override
	public int numberOfKeys() {
		
		return letters.length;
	}
}
