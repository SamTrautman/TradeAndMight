package forms;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import core.World;
import core.terrain.LandTerrain;
import core.terrain.SeaTerrain;

public class WorldMap extends JPanel {
	
	private int SPRITE_SIZE = 16;

	private static final long serialVersionUID = -1228684010205095669L;

	public WorldMap() {
		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {
				replaceTerrain(e.getX() / SPRITE_SIZE, e.getY() / SPRITE_SIZE);
				repaint();
			}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {} 
		});
		
		this.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				if ((e.getX() > 0) && (e.getY() > 0) && (e.getX() < SPRITE_SIZE * World.getInstance().getSizeX()) && (e.getY() < SPRITE_SIZE * World.getInstance().getSizeY())) {
					if (World.getInstance().getTerrain(e.getX() / SPRITE_SIZE, e.getY() / SPRITE_SIZE) instanceof SeaTerrain) {
						replaceTerrain(e.getX() / SPRITE_SIZE, e.getY() / SPRITE_SIZE);
						repaint();
					}
				}
			}
		});
	}
	
	private void replaceTerrain(int x, int y) {
		World.getInstance().setTerrain(x, y, new LandTerrain());
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
