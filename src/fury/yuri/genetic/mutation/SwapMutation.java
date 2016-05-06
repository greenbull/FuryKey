package fury.yuri.genetic.mutation;

import java.util.Map;
import java.util.Random;

import fury.yuri.keyboard.Keyboard;

public class SwapMutation implements IMutation {
	
	private double mutationRate;
	private Random rand;
	
	public SwapMutation(double mutationRate) {

		if(mutationRate < 0.0 || mutationRate > 1.0) {
			mutationRate = 0.0;
		}
		this.mutationRate = mutationRate;
		this.rand = new Random();
	}

	@Override
	public void mutate(Keyboard k) {
		
		Map<Integer, Character> positionKeyMap = k.getPositionKeyMap();
		int size = positionKeyMap.size();
		int mutationNumber = (int) (mutationRate*size);
		
		for(int i=0; i<mutationNumber; i++) {
			int randomPosition1 = rand.nextInt(size) + 1;
			int randomPosition2 = rand.nextInt(size) + 1;
			k.swapKeys(randomPosition1, randomPosition2);
		}
	}

}
