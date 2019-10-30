package forms;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import core.World;
import core.terrain.LandTerrain;
import core.terrain.SeaTerrain;

public class WorldMap extends JPanel {
	
	private int SPRITE_SIZE = 16;

	private static final long serialVersionUID = -1228684010205095669L;

	public WorldMap() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (int i = 0; i < World.getInstance().getSizeX(); i++) {
			for (int j = 0; j < World.getInstance().getSizeY(); j++) {
				
				if (World.getInstance().getTerrain(i, j) instanceof SeaTerrain) {
					g.setColor(Color.blue);
				}
				else if (World.getInstance().getTerrain(i, j) instanceof LandTerrain) {
					g.setColor(Color.green);
				}
				
				g.fillRect(i * SPRITE_SIZE, j * SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE);
				g.setColor(Color.gray);
				g.drawRect(i * SPRITE_SIZE, j * SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE);
			}
		}
	}

}
