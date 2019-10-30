package forms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainForm extends JFrame {
	
	private JPanel MainPanel;
	private JPanel SidePanel;
	private JPanel TopPanel;

	private static final long serialVersionUID = -7185997901657789642L;

	public MainForm() throws HeadlessException {
		setSize(800,600);
		
		buildBaseForm();
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void buildBaseForm() {
		TopPanel = new JPanel();
		TopPanel.setBackground(Color.gray);
		
		SidePanel = new JPanel();
		SidePanel.setPreferredSize(new Dimension(200, 0));
		SidePanel.setBackground(Color.yellow);
		
		MainPanel = new WorldMap();
		MainPanel.setBackground(Color.green);
		
		getContentPane().add(TopPanel, BorderLayout.NORTH);
		getContentPane().add(SidePanel, BorderLayout.WEST);
		getContentPane().add(MainPanel, BorderLayout.CENTER);
	}

}
