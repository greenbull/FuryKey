package fury.yuri.keyboard.util;

import java.util.ArrayList;
import java.util.List;

import fury.yuri.keyboard.IKeys;
import fury.yuri.keyboard.Keyboard;
import fury.yuri.keyboard.KeysEN;
import fury.yuri.keyboard.layout.ILayout;
import fury.yuri.keyboard.layout.Rows4Layout;
import fury.yuri.keyboard.layout.StandardLayout;

public class KeyboardUtility {
	
	public static List<Keyboard> generateStandardLayoutEN(int n) {
		
		return generateKeyboards(new StandardLayout(), KeysEN.getInstance(), n);
	}
	
	public static List<Keyboard> generateRandomLayoutEN(int n) {
		
		int half1 = n/2;
		int half2 = n-half1;
		List<Keyboard> result = new ArrayList<>();
		result.addAll(generateKeyboards(new Rows4Layout(), KeysEN.getInstance(), half1));
		result.addAll(generateKeyboards(new StandardLayout(), KeysEN.getInstance(), half2));
		
		return result;
	}
	
	public static List<Keyboard> generateRow4LayoutEN(int n) {
		
		return generateKeyboards(new Rows4Layout(), KeysEN.getInstance(), n);
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
