package fury.yuri.keyboard;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fury.yuri.keyboard.layout.ILayout;

public class Keyboard implements Comparable<Keyboard> {
	
	private double cost;
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
	
	public ILayout getLayout() {
		return layout;
	}
	
	public IKeys getKeys() {
		return keys;
	}
	
	public int getPositionFor(char letter) {
		//TODO ime sve govori
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
	
	public void calculateCost() {
		
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
		
		this.cost = t;
	}
	
	@Override
	public String toString() {
		
		return layout.keyboardToString(positionKeyMap);
	}
	
	public void toFile(File file) {
		// TODO zapisat tipkovnicu u nekom obliku u file
	}
	
	public double getCost() {
		return cost;
	}
	
	public Map<Integer, Character> getPositionKeyMap() {
		return positionKeyMap;
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
		
		if(this.cost < o.getCost()) {
			return -1;
		} else if(this.cost > o.getCost()) {
			return 1;
		} else {
			return 0;
		}
	}
}
