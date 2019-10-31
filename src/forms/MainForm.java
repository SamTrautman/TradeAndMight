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

public class MainForm extends JFrame {
	
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
	
	private void closeForm() {
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
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
		
		MainPanel = new WorldMap();
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
		ItmNewWorld.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				NewWorldDialog dlg = new NewWorldDialog();
				dlg.setModalityType(ModalityType.APPLICATION_MODAL);
				
				dlg.setVisible(true);
				
				if (dlg.Result) {
					World.getInstance().createNewWorld(Integer.parseInt(dlg.txtSizeX.getText()), Integer.parseInt(dlg.txtSizeY.getText()));
					repaint();
				}
			}
		});
		
		JMenuItem ItmSaveWorld = new JMenuItem("Speichern");
		ItmSaveWorld.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JMenuItem ItmLoadWorld = new JMenuItem("Laden");
		ItmLoadWorld.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JMenuItem ItmClose = new JMenuItem("Beenden");
		ItmClose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				closeForm();
			}
		});
		
		JMenu MnuFile = new JMenu("Datei");
		MnuFile.add(ItmNewWorld);
		MnuFile.add(ItmSaveWorld);
		MnuFile.add(ItmLoadWorld);
		MnuFile.add(ItmClose);
		
		MainMenuBar.add(MnuFile);
	}
	
	private void buildEditMenu() {
		JMenuItem ItmEditTerrain = new JMenuItem("Terrain");
		ItmEditTerrain.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SidePanel.removeAll();
				SidePanel.add(EdtTerrainFrame, BorderLayout.CENTER);
				revalidate();
			}
		});
		
		JMenu MnuEdit = new JMenu("Bearbeiten");
		MnuEdit.add(ItmEditTerrain);
		
		MainMenuBar.add(MnuEdit);
	}
}
