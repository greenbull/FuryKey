package fury.yuri.keyboard.test;

import java.util.List;

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
import fury.yuri.keyboard.layout.StandardLayout;
import fury.yuri.keyboard.util.KeyboardUtility;

public class TestMain {

	public static void main(String[] args) {
		
//		IMutation mutation = new SwapMutation(0.3);
//		ISelection selection = new TournamentSelection(24);
//		ICrossover crossover = new StupidCrossover();
//		int generationSize = 500;
//		int generationsNumber = 100;
//		double mutationChance = 0.14;
//		double crossoverChance = 0.8;
//
//		GeneticAlgorithm alg = new GeneticAlgorithm(mutation, selection, crossover, generationSize, generationsNumber,
//				mutationChance, crossoverChance);
//		alg.run();
		
		Keyboard k1 = KeyboardUtility.generateStandardLayoutEN(1).get(0);
		Keyboard k2 = KeyboardUtility.generateRow4LayoutEN(1).get(0);
		
		StupidCrossover crossover = new StupidCrossover();
		List<Keyboard> children = crossover.cross(k1, k2);
		
		System.out.println("Parent1:");
		System.out.println(k1);
		System.out.println("Parent2:");
		System.out.println(k2);
		System.out.println("Child1:");
		System.out.println(children.get(0));
		System.out.println("Child2:");
		System.out.println(children.get(1));
	}
}
