package fury.yuri.genetic.selection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fury.yuri.keyboard.Keyboard;

public class TournamentSelection implements ISelection {
	
	private int x;
	
	public TournamentSelection(int x) {
		this.x = x;
	}

	@Override
	public List<Keyboard> select(List<Keyboard> keyboards, int n) {
		
		if(n > x) {
			n = x;
		}
		
		List<Integer> indices = new ArrayList<>();
		for(int i=0; i<keyboards.size(); i++) {
			indices.add(i);
		}
		
		Collections.shuffle(indices);
		List<Keyboard> selected = new ArrayList<>();
		for(int i=0; i<x; i++) {
			selected.add(keyboards.get(indices.get(i)));
		}
		Collections.sort(selected);
		List<Keyboard> result = new ArrayList<>();
		for(int i=0; i<n; i++) {
			result.add(selected.get(i));
		}
		
		return result;
	}
}
