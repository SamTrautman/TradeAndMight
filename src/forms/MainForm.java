package forms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import core.World;
import core.terrain.LandTerrain;
import core.terrain.MountainTerrain;
import core.terrain.SeaTerrain;

public class MainForm extends JFrame implements ActionListener {
	
	private JMenuBar MainMenuBar;
	private JPanel MainPanel;
	private JPanel SidePanel;
	private JPanel TopPanel;
	
	private EditTerrainFrame EdtTerrainFrame;

	private static final long serialVersionUID = -7185997901657789642L;

	public MainForm() throws HeadlessException {
		setSize(800,600);
		
		buildBaseForm();
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("NewWorld")) {
			NewWorldDialog dlg = new NewWorldDialog();
			dlg.setModalityType(ModalityType.APPLICATION_MODAL);
			
			dlg.setVisible(true);
			
			if (dlg.Result) {
				World.getInstance().createNewWorld(Integer.parseInt(dlg.txtSizeX.getText()), Integer.parseInt(dlg.txtSizeY.getText()));
				repaint();
			}
		}
		else if (e.getActionCommand().equals("SaveMap")) {
			
		}
		else if (e.getActionCommand().equals("LoadMap")) {
			
		}
		else if (e.getActionCommand().equals("Close")) {
			dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
		else if (e.getActionCommand().equals("EditTerrain")) {
			SidePanel.removeAll();
			SidePanel.add(EdtTerrainFrame, BorderLayout.CENTER);
			revalidate();
		}
	}
	
	public void editTile(int X, int Y) {
		if ((EdtTerrainFrame.rbSea.isSelected()) && !(World.getInstance().getTerrain(X, Y) instanceof SeaTerrain)) {
			World.getInstance().setTerrain(X, Y, new SeaTerrain());
		}
		else if ((EdtTerrainFrame.rbLand.isSelected()) && !(World.getInstance().getTerrain(X, Y) instanceof LandTerrain)) {
			World.getInstance().setTerrain(X, Y, new LandTerrain());
		}
		else if ((EdtTerrainFrame.rbMountain.isSelected()) && !(World.getInstance().getTerrain(X, Y) instanceof MountainTerrain)) {
			World.getInstance().setTerrain(X, Y, new MountainTerrain());
		}
	}
	
	private void buildBaseForm() {
		MainMenuBar = new JMenuBar();
		setJMenuBar(MainMenuBar);
		
		buildMenu();
		
		TopPanel = new JPanel();
		TopPanel.setBackground(Color.gray);
		
		SidePanel = new JPanel();
		SidePanel.setLayout(new BorderLayout());
		SidePanel.setPreferredSize(new Dimension(200, 0));
		SidePanel.setBackground(Color.yellow);
		
		MainPanel = new WorldMap(this);
		MainPanel.setBackground(Color.green);
		
		EdtTerrainFrame = new EditTerrainFrame();
		
		getContentPane().add(TopPanel, BorderLayout.NORTH);
		getContentPane().add(SidePanel, BorderLayout.WEST);
		getContentPane().add(MainPanel, BorderLayout.CENTER);
	}
	
	private void buildMenu() {
		buildFileMenu();
		buildEditMenu();
	}
	
	private void buildFileMenu() {
		JMenuItem ItmNewWorld = new JMenuItem("Neue Welt");
		JMenuItem ItmSaveWorld = new JMenuItem("Speichern");
		JMenuItem ItmLoadWorld = new JMenuItem("Laden");
		JMenuItem ItmClose = new JMenuItem("Beenden");
		
		ItmNewWorld.setActionCommand("NewWorld");
		ItmSaveWorld.setActionCommand("SaveMap");
		ItmLoadWorld.setActionCommand("LoadMap");
		ItmClose.setActionCommand("Close");
		
		ItmNewWorld.addActionListener(this);
		ItmSaveWorld.addActionListener(this);
		ItmLoadWorld.addActionListener(this);
		ItmClose.addActionListener(this);
		
		JMenu MnuFile = new JMenu("Datei");
		MnuFile.add(ItmNewWorld);
		MnuFile.add(ItmSaveWorld);
		MnuFile.add(ItmLoadWorld);
		MnuFile.add(ItmClose);
		
		MainMenuBar.add(MnuFile);
	}
	
	private void buildEditMenu() {
		JMenuItem ItmEditTerrain = new JMenuItem("Terrain");
		ItmEditTerrain.setActionCommand("EditTerrain");
		ItmEditTerrain.addActionListener(this);
		
		JMenu MnuEdit = new JMenu("Bearbeiten");
		MnuEdit.add(ItmEditTerrain);
		
		MainMenuBar.add(MnuEdit);
	}
}
