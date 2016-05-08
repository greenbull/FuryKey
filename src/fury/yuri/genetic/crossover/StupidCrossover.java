package fury.yuri.genetic.crossover;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import fury.yuri.keyboard.Keyboard;

public class StupidCrossover implements ICrossover {

	private Random rand;

	public StupidCrossover() {
		this.rand = new Random();
	}

	@Override
	public List<Keyboard> cross(Keyboard k1, Keyboard k2) {

		int n = k1.getLayout().numberOfKeys();

		List<Character> genome1 = new ArrayList<>();
		List<Character> genome2 = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			genome1.add(k1.getKeyFor(i + 1));
		}
		for (int i = 0; i < n; i++) {
			genome2.add(k2.getKeyFor(i + 1));
		}

		int splitIndex = n / 2;

		List<Character> childGenome1 = new ArrayList<>();
		List<Character> childGenome2 = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			if (i < splitIndex) {
				childGenome1.add(genome1.get(i));
				childGenome2.add(genome2.get(i));
			} else {
				childGenome1.add(genome2.get(i));
				childGenome2.add(genome1.get(i));
			}
		}

		fix(childGenome1, k1.getKeys().getKeys());
		fix(childGenome2, k2.getKeys().getKeys());
		
		Map<Integer, Character> positionKeyMap1 = new HashMap<>();
		Map<Integer, Character> positionKeyMap2 = new HashMap<>();
		
		for(int i=0; i<childGenome1.size(); i++) {
			positionKeyMap1.put(i+1, childGenome1.get(i));
		}
		for(int i=0; i<childGenome2.size(); i++) {
			positionKeyMap2.put(i+1, childGenome2.get(i));
		}
		
		List<Keyboard> result = new ArrayList<>();
		result.add(new Keyboard(k1.getLayout(), k1.getKeys(), positionKeyMap1));
		result.add(new Keyboard(k2.getLayout(), k2.getKeys(), positionKeyMap2));
		
		return result;
	}

	private void fix(List<Character> genome1, char[] letters) {

		List<Character> duplicates = new ArrayList<>();
		List<Character> missing = new ArrayList<>();

		for (int i = 0; i < letters.length; i++) {
			if (!genome1.contains(letters[i])) {
				missing.add(letters[i]);
			}
		}

		Set<Character> temp = new HashSet<>();
		for (Character c : genome1) {
			if (!temp.add(c)) {
				duplicates.add(c);
			}
		}

		Collections.shuffle(duplicates);
		Collections.shuffle(missing);

		int count = 0;
		for (Character dup : duplicates) {
			int[] positions = findDuplicatePositions(dup, genome1);
			int picked = -1;
			double chance = rand.nextDouble();
			if (chance < 0.5) {
				picked = positions[0];
			} else {
				picked = positions[1];
			}
			genome1.set(picked, missing.get(count));
			count++;
		}
	}

	private int[] findDuplicatePositions(Character dup, List<Character> genome1) {
		
		int[] result = new int[2];
		int i=0;
		int counter=0;
		for(Character c : genome1) {
			if(c.equals(dup)) {
				result[i] = counter;
				i++;
			}
			counter++;
		}
		
		return result;
	}
}
