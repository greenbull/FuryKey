package fury.yuri.genetic.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fury.yuri.genetic.crossover.ICrossover;
import fury.yuri.genetic.mutation.IMutation;
import fury.yuri.genetic.selection.ISelection;
import fury.yuri.keyboard.Keyboard;
import fury.yuri.keyboard.util.KeyboardUtility;

public class GeneticAlgorithm {

	private IMutation mutation;
	private ISelection selection;
	private ICrossover crossover;
	private int generationSize;
	private int generationsNumber;

	private List<IGAListener> listeners;

	public GeneticAlgorithm(IMutation mutation, ISelection selection, ICrossover crossover, int generationSize,
			int generationsNumber) {

		this.mutation = mutation;
		this.selection = selection;
		this.crossover = crossover;
		this.generationSize = generationSize;
		this.generationsNumber = generationsNumber;
		this.listeners = new ArrayList<>();
	}

	public void run() {

		List<Keyboard> currentGeneration = KeyboardUtility.generateStandardLayoutEN(generationSize);
		
		for(int i=0; i<generationsNumber; i++) {
			
			evaluateGeneration(currentGeneration);
			Collections.sort(currentGeneration);
			
			List<Keyboard> newGeneration = new ArrayList<>();
			newGeneration.add(currentGeneration.get(0));
			
			while(newGeneration.size() < generationSize) {
				//TODO smislit način elegantan i promjenjiv
			}
		}
	}

	private void evaluateGeneration(List<Keyboard> currentGeneration) {
		
		for(Keyboard keyboard : currentGeneration) {
			keyboard.calculateCost();
		}
	}
}
