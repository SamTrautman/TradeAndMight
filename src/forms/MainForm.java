package forms;

import java.awt.HeadlessException;

import javax.swing.JFrame;

public class MainForm extends JFrame {

	private static final long serialVersionUID = -7185997901657789642L;

	public MainForm() throws HeadlessException {
		setSize(800,600);
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
