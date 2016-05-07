package fury.yuri.genetic.crossover;

import java.util.List;

import fury.yuri.keyboard.Keyboard;

public class StupidCrossover implements ICrossover {

	@Override
	public List<Keyboard> cross(Keyboard k1, Keyboard k2) {
	
		//TODO ovo je samo implementacija za isti layout i iste tipke, za razlicite layoutei razlicite tipke napravit nesto drugacije...
		char[] letters = k1.getKeys().getKeys();
		//pola ovih slova zamijenit
	}

}
