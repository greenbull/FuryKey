package fury.yuri.keyboard.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fury.yuri.genetic.algorithm.GeneticAlgorithm;
import fury.yuri.genetic.crossover.ICrossover;
import fury.yuri.genetic.crossover.StupidCrossover;
import fury.yuri.genetic.mutation.IMutation;
import fury.yuri.genetic.mutation.SwapMutation;
import fury.yuri.genetic.selection.ISelection;
import fury.yuri.genetic.selection.RouletteWheelSelection;
import fury.yuri.genetic.selection.TournamentSelection;
import fury.yuri.keyboard.Keyboard;
import fury.yuri.keyboard.KeysEN;
import fury.yuri.keyboard.layout.ILayout;
import fury.yuri.keyboard.layout.QWERTYLayout;
import fury.yuri.keyboard.layout.ReverseFitalyLayout;
import fury.yuri.keyboard.layout.StandardLayout;
import fury.yuri.keyboard.util.KeyboardUtility;

public class TestMain {

	public static void main(String[] args) {
		
		IMutation mutation = new SwapMutation(0.3);
		ISelection selection = new TournamentSelection(24);
		ICrossover crossover = new StupidCrossover();
		int generationSize = 500;
		int generationsNumber = 1000;
		double mutationChance = 0.14;
		double crossoverChance = 0.8;

		GeneticAlgorithm alg = new GeneticAlgorithm(mutation, selection, crossover, generationSize, generationsNumber,
				mutationChance, crossoverChance);
		alg.run();
		
//		Map<Integer, Character> qwertyMap = new HashMap<>();
//		qwertyMap.put(1, 'q');
//		qwertyMap.put(2, 'w');
//		qwertyMap.put(3, 'e');
//		qwertyMap.put(4, 'r');
//		qwertyMap.put(5, 't');
//		qwertyMap.put(6, 'y');
//		qwertyMap.put(7, 'u');
//		qwertyMap.put(8, 'i');
//		qwertyMap.put(9, 'o');
//		qwertyMap.put(10, 'p');
//		qwertyMap.put(11, 'a');
//		qwertyMap.put(12, 's');
//		qwertyMap.put(13, 'd');
//		qwertyMap.put(14, 'f');
//		qwertyMap.put(15, 'g');
//		qwertyMap.put(16, 'h');
//		qwertyMap.put(17, 'j');
//		qwertyMap.put(18, 'k');
//		qwertyMap.put(19, 'l');
//		qwertyMap.put(20, 'z');
//		qwertyMap.put(21, 'x');
//		qwertyMap.put(22, 'c');
//		qwertyMap.put(23, 'v');
//		qwertyMap.put(24, 'b');
//		qwertyMap.put(25, 'n');
//		qwertyMap.put(26, 'm');
//		
//		Keyboard qwerty = new Keyboard(new QWERTYLayout(), KeysEN.getInstance(), qwertyMap);
//		qwerty.calculateFitness();
//		System.out.println(qwerty.getFitness());
		
//		Keyboard k1 = KeyboardUtility.generateStandardLayoutEN(1).get(0);
//		Keyboard k2 = KeyboardUtility.generateRow4LayoutEN(1).get(0);
//		
//		StupidCrossover crossover = new StupidCrossover();
//		List<Keyboard> children = crossover.cross(k1, k2);
//		
//		System.out.println("Parent1:");
//		System.out.println(k1);
//		System.out.println("Parent2:");
//		System.out.println(k2);
//		System.out.println("Child1:");
//		System.out.println(children.get(0));
//		System.out.println("Child2:");
//		System.out.println(children.get(1));
	}
}
