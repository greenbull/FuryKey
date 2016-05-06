package fury.yuri.keyboard;

import java.io.File;

public interface IKeys {

	char[] getKeys();
	double getProbabilityFor(char key1, char key2);
	int numberOfKeys();
}