package fury.yuri.keyboard;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import fury.yuri.keyboard.layout.ILayout;

public class Keyboard implements Comparable<Keyboard> {
	
	private double fitness;
	// pozicije mapiramo od 1 !!!!
	private Map<Integer, Character> positionKeyMap;
	private ILayout layout;
	private IKeys keys;
	
	public Keyboard(ILayout layout, IKeys keys) {
		positionKeyMap = new HashMap<>();
		this.layout = layout;
		this.keys = keys;
		generateKeyboard();
	}
	
	public Keyboard(ILayout layout, IKeys keys, Map<Integer, Character> positionKeyMap) {
		this.layout = layout;
		this.keys = keys;
		this.positionKeyMap = positionKeyMap;
	}
	
	public Keyboard copy() {
		
		Map<Integer, Character> copyMap = new HashMap<>(positionKeyMap);
		Keyboard newKeyboard = new Keyboard(layout, keys);
		newKeyboard.setPositionKeyMap(copyMap);
		return newKeyboard;
	}
	
	public void setPositionKeyMap(Map<Integer, Character> positionKeyMap) {
		this.positionKeyMap = positionKeyMap;
	}
	
	public ILayout getLayout() {
		return layout;
	}
	
	public IKeys getKeys() {
		return keys;
	}
	
	public int getPositionFor(char letter) {
		
		for(Entry<Integer, Character> entry : positionKeyMap.entrySet()) {
			if(entry.getValue() == letter) {
				return entry.getKey();
			}
		}
		
		return -1;
	}
	
	public char getKeyFor(int position) {
		
		return positionKeyMap.get(position);
	}
	
	public Map<Integer, Character> getPositionKeyMap() {
		return positionKeyMap;
	}
	
	private void generateKeyboard() {
		
		List<Integer> values = new ArrayList<>();
		for(int i=1; i<=layout.numberOfKeys(); i++) {
			values.add(i);
		}
		Collections.shuffle(values);
		
		char[] letters = keys.getKeys();
		for(int i=0; i<letters.length; i++) {
			positionKeyMap.put(values.get(i), letters[i]);
		}
	}
	
	public void calculateFitness() {
		
		double t = 0.0;
		int N = layout.numberOfKeys();
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				double probability = keys.getProbabilityFor(positionKeyMap.get(i), positionKeyMap.get(j));
				double distance = layout.getDistance(i, j);
				double num = distance + 1;
				t += (probability*(Math.log(num) / Math.log(2)));
			}
		}
		
		this.fitness = 1.0/t;
	}
	
	public void setFitness(double fitness) {
		this.fitness = fitness;
	}
	
	@Override
	public String toString() {
		
//		return Double.toString(fitness);
		
		return layout.keyboardToString(positionKeyMap);
	}
	
	public void toFile(File file) {
		// TODO zapisat tipkovnicu u nekom obliku u file
	}
	
	public double getFitness() {
		return fitness;
	}
	
	public void swapKeys(int pos1, int pos2) {
		
		if(pos1 == pos2) {
			return;
		}
		
		char key1 = positionKeyMap.get(pos1);
		char key2 = positionKeyMap.get(pos2);
		positionKeyMap.put(pos1, key2);
		positionKeyMap.put(pos2, key1);
	}

	@Override
	public int compareTo(Keyboard o) {
		
		if(this.fitness < o.getFitness()) {
			return 1;
		} else if(this.fitness > o.getFitness()) {
			return -1;
		} else {
			return 0;
		}
	}
}
