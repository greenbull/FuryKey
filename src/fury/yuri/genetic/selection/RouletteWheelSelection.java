package fury.yuri.genetic.selection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fury.yuri.keyboard.Keyboard;

public class RouletteWheelSelection implements ISelection {

	@Override
	public List<Keyboard> select(List<Keyboard> keyboards, int n) {
		
		List<Keyboard> copyList = new ArrayList<>(keyboards);
		List<Keyboard> result = new ArrayList<>();
		
		for(int i=0; i<n; i++) {
			Keyboard k = selectFrom(copyList);
			copyList.remove(k);
			result.add(k);
		}
		
		return result;
	}
	
	private Keyboard selectFrom(List<Keyboard> keyboards) {
		
		double sum = 0.0;
		for(Keyboard keyboard : keyboards) {
			sum += keyboard.getFitness();
		}
		Random rand = new Random();
		double pick = rand.nextDouble()*sum;
		double min = 0.0;
		
		for(Keyboard keyboard : keyboards) {
			
			if(pick >= min && pick < keyboard.getFitness()+min) {
				return keyboard;
			}
			min += keyboard.getFitness();
		}
		
		return null;
	}
}
