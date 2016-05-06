package fury.yuri.genetic.selection;

import java.util.List;

import fury.yuri.keyboard.Keyboard;

public interface ISelection {

	Keyboard select(List<Keyboard> keyboards);
}
