package fury.yuri.keyboard;

public interface IKeys {

	char[] getKeys();
	double getProbabilityFor(char key1, char key2);
	int numberOfKeys();
}