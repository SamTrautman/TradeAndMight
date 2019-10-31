package forms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import core.World;
import core.WorldImExporter;
import core.terrain.TerrainType;

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
			JFileChooser chooser = new JFileChooser();
			if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				WorldImExporter.getInstance().saveWorldToFile(chooser.getSelectedFile().getAbsolutePath());
			}
		}
		else if (e.getActionCommand().equals("LoadMap")) {
			JFileChooser chooser = new JFileChooser();
			if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				WorldImExporter.getInstance().loadWorldFromFile(chooser.getSelectedFile().getAbsolutePath());
				repaint();
			}
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
		if ((EdtTerrainFrame.rbSea.isSelected()) && !(World.getInstance().getTerrain(X, Y).getTerrainType() == TerrainType.TERRAIN_SEA)) {
			World.getInstance().setTerrain(X, Y, TerrainType.TERRAIN_SEA);
		}
		else if ((EdtTerrainFrame.rbLand.isSelected()) && !(World.getInstance().getTerrain(X, Y).getTerrainType() == TerrainType.TERRAIN_LAND)) {
			World.getInstance().setTerrain(X, Y, TerrainType.TERRAIN_LAND);
		}
		else if ((EdtTerrainFrame.rbMountain.isSelected()) && !(World.getInstance().getTerrain(X, Y).getTerrainType() == TerrainType.TERRAIN_MOUNTAIN)) {
			World.getInstance().setTerrain(X, Y, TerrainType.TERRAIN_MOUNTAIN);
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
