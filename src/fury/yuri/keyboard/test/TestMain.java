package fury.yuri.keyboard.test;

import fury.yuri.genetic.mutation.IMutation;
import fury.yuri.genetic.mutation.SwapMutation;
import fury.yuri.keyboard.Keyboard;
import fury.yuri.keyboard.KeysEN;
import fury.yuri.keyboard.layout.ILayout;
import fury.yuri.keyboard.layout.StandardLayout;

public class TestMain {

	public static void main(String[] args) {
		
		ILayout standardLayout = new StandardLayout();
		Keyboard keyboard = new Keyboard(standardLayout, KeysEN.getInstance());
		System.out.println(keyboard);
		keyboard.calculateCost();
		System.out.println("COST: " + keyboard.getCost());
		IMutation mutation = new SwapMutation(0.2);
		mutation.mutate(keyboard);
		System.out.println(keyboard);
		keyboard.calculateCost();
		System.out.println("COST: " + keyboard.getCost());
	}
}
