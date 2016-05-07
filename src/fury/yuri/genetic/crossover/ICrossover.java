package fury.yuri.genetic.crossover;

import java.util.List;

import fury.yuri.keyboard.Keyboard;

public interface ICrossover {

	List<Keyboard> cross(Keyboard k1, Keyboard k2);
}
