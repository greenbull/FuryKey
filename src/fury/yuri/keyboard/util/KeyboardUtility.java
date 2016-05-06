package fury.yuri.keyboard.util;

import java.util.ArrayList;
import java.util.List;

import fury.yuri.keyboard.IKeys;
import fury.yuri.keyboard.Keyboard;
import fury.yuri.keyboard.KeysEN;
import fury.yuri.keyboard.layout.ILayout;
import fury.yuri.keyboard.layout.StandardLayout;

public class KeyboardUtility {
	
	public static List<Keyboard> generateStandardLayoutEN(int n) {
		
		return generateKeyboards(new StandardLayout(), KeysEN.getInstance(), n);
	}

	private static List<Keyboard> generateKeyboards(ILayout layout, IKeys keys, int n) {
		
		if(n <= 0) {
			return null;
		}
		List<Keyboard> keyboards = new ArrayList<>();
		for(int i=0; i<n; i++) {
			keyboards.add(new Keyboard(layout, keys));
		}
		return keyboards;
	}
}
