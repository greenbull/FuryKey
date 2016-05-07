package fury.yuri.genetic.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
	
	private double mutationChance;
	private double crossoverChance;

	private List<IGAListener> listeners;
	
	private Random rand;

	public GeneticAlgorithm(IMutation mutation, ISelection selection, ICrossover crossover, int generationSize,
			int generationsNumber, double mutationChance, double crossoverChance) {

		this.mutation = mutation;
		this.selection = selection;
		this.crossover = crossover;
		this.generationSize = generationSize;
		this.generationsNumber = generationsNumber;
		this.mutationChance = mutationChance;
		this.crossoverChance = crossoverChance;
		this.rand = new Random();
		this.listeners = new ArrayList<>();
	}

	public void run() {

		List<Keyboard> currentGeneration = KeyboardUtility.generateStandardLayoutEN(generationSize);
		
		for(int i=0; i<generationsNumber; i++) {
			
			evaluateGeneration(currentGeneration);
			Collections.sort(currentGeneration);
			
			List<Keyboard> newGeneration = new ArrayList<>();
			
			while(newGeneration.size() < generationSize) {
				
				double chance = rand.nextDouble();
				
				if(chance < mutationChance) {
					//do mutation
					List<Keyboard> selected = selection.select(currentGeneration, 1);
					mutation.mutate(selected.get(0));
					newGeneration.add(selected.get(0));
				} else if(chance >= mutationChance && chance < mutationChance+crossoverChance) {
					//do crossover
					List<Keyboard> parents = selection.select(currentGeneration, 2);
					List<Keyboard> children = crossover.cross(parents.get(0), parents.get(1));
					newGeneration.addAll(children);
				} else {
					List<Keyboard> selected = selection.select(currentGeneration, 1);
					newGeneration.addAll(selected);
				}
			}
		}
	}

	private void evaluateGeneration(List<Keyboard> currentGeneration) {
		
		for(Keyboard keyboard : currentGeneration) {
			keyboard.calculateCost();
		}
	}
}
