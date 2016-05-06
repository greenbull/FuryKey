package fury.yuri.genetic.selection;

import java.util.List;
import java.util.Random;

import fury.yuri.keyboard.Keyboard;

public class RouletteWheelSelection implements ISelection {

	@Override
	public Keyboard select(List<Keyboard> keyboards) {
		
		double sum = 0.0;
		for(Keyboard keyboard : keyboards) {
			sum += keyboard.getCost();
		}
		Random rand = new Random();
		double pick = rand.nextDouble()*sum;
		double min = 0.0;
		
		for(Keyboard keyboard : keyboards) {
			
			if(pick >= min && pick < keyboard.getCost()+min) {
				return keyboard;
			}
			min += keyboard.getCost();
		}
		
		return null;
	}
}
