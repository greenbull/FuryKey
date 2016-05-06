package fury.yuri.keyboard.test;

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
	}
}
