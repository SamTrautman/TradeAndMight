package launch;

import core.World;
import forms.MainForm;

public class Main {

	public static void main(String[] args) {
		
		//TEST
		World.getInstance().createNewWorld(25, 25);
		//TEST END
		
		MainForm Form = new MainForm();
		Form.setVisible(true);
	}

}
