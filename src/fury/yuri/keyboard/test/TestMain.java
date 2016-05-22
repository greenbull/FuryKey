package fury.yuri.keyboard.test;

import java.io.File;
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
import fury.yuri.keyboard.layout.OneHandedLayout;
import fury.yuri.keyboard.layout.QWERTYLayout;
import fury.yuri.keyboard.layout.ReverseFitalyLayout;
import fury.yuri.keyboard.layout.StandardLayout;
import fury.yuri.keyboard.layout.TwoHandedLayout;
import fury.yuri.keyboard.util.KeyboardUtility;

public class TestMain {

	public static void main(String[] args) {
		
		//u argumente stavit layout1 za one-handed, layout2 za two handed
		//ako se nešto krivo stavi dogodit će se exception
		
		Map<String, String> arguments = new HashMap<>();
		arguments.put("mutationRate", "0.3");
		arguments.put("selectionConst", "24");
		arguments.put("generationSize", "500");
		arguments.put("generationsNum", "200");
		arguments.put("mutationChance", "0.14");
		arguments.put("crossoverChance", "0.8");
		
		for(int i=0; i<args.length; i+=2) {
			arguments.put(args[i].substring(1), args[i+1]);
		}
		
		ILayout layout = null;
		if(arguments.containsKey("layout1")) {
			layout = new OneHandedLayout(arguments.get("layout1"), new File(arguments.get("layout1")));
		}
		else if(arguments.containsKey("layout2")) {
			layout = new TwoHandedLayout(arguments.get("layout2"), new File(arguments.get("layout2")));
		} else {
			layout = new OneHandedLayout("layouts/standard.lay", new File("layouts/standard.lay"));
		}
		IMutation mutation = new SwapMutation(Double.parseDouble(arguments.get("mutationRate")));
		ISelection selection = new TournamentSelection(Integer.parseInt(arguments.get("selectionConst")));
		ICrossover crossover = new StupidCrossover();
		int generationSize = Integer.parseInt(arguments.get("generationSize"));
		int generationsNumber = Integer.parseInt(arguments.get("generationsNum"));
		double mutationChance = Double.parseDouble(arguments.get("mutationChance"));
		double crossoverChance = Double.parseDouble(arguments.get("crossoverChance"));

		GeneticAlgorithm alg = new GeneticAlgorithm(layout, mutation, selection, crossover, generationSize, generationsNumber,
				mutationChance, crossoverChance);
		alg.run();

		
//		Map<Integer, Character> fitaly = new HashMap<>();
//		fitaly.put(1, 'z');
//		fitaly.put(2, 'v');
//		fitaly.put(3, 'c');
//		fitaly.put(4, 'h');
//		fitaly.put(5, 'w');
//		fitaly.put(6, 'k');
//		fitaly.put(7, 'f');
//		fitaly.put(8, 'i');
//		fitaly.put(9, 't');
//		fitaly.put(10, 'a');
//		fitaly.put(11, 'l');
//		fitaly.put(12, 'y');
//		fitaly.put(13, 'n');
//		fitaly.put(14, 'e');
//		fitaly.put(15, 'g');
//		fitaly.put(16, 'd');
//		fitaly.put(17, 'o');
//		fitaly.put(18, 'r');
//		fitaly.put(19, 's');
//		fitaly.put(20, 'b');
//		fitaly.put(21, 'q');
//		fitaly.put(22, 'j');
//		fitaly.put(23, 'u');
//		fitaly.put(24, 'm');
//		fitaly.put(25, 'p');
//		fitaly.put(26, 'x');
//		ILayout l = new OneHandedLayout("FITALY", new File("layouts/fitaly.lay"));
//		Keyboard k = new Keyboard(l, KeysEN.getInstance(), fitaly);
//		System.out.println(k);
//		k.calculateFitness();
//		System.out.println(k.getFitness());
		
		
		
//		OneHandedLayout l = new OneHandedLayout("Reverse FITALY", new File("layouts/rows4.lay"));
//		Keyboard k = new Keyboard(l, KeysEN.getInstance());
//		System.out.println(k);
		
//		IMutation mutation = new SwapMutation(0.3);
//		ISelection selection = new TournamentSelection(24);
//		ICrossover crossover = new StupidCrossover();
//		int generationSize = 500;
//		int generationsNumber = 1000;
//		double mutationChance = 0.14;
//		double crossoverChance = 0.8;
//
//		GeneticAlgorithm alg = new GeneticAlgorithm(mutation, selection, crossover, generationSize, generationsNumber,
//				mutationChance, crossoverChance);
//		alg.run();
		
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
//		Keyboard qwerty = new Keyboard(new OneHandedLayout("qwery", new File("layouts/qwerty.lay")), KeysEN.getInstance(), qwertyMap);
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
