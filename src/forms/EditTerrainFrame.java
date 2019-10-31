package forms;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class EditTerrainFrame extends JPanel {

	private static final long serialVersionUID = 2870273880928195718L;

	public JRadioButton rbSea;
	public JRadioButton rbLand;
	public JRadioButton rbMountain;
	
	public EditTerrainFrame() {
		rbSea = new JRadioButton("Ozean");
		rbSea.setSelected(true);
		
		rbLand = new JRadioButton("Land");
		rbMountain = new JRadioButton("Berge");
		
		ButtonGroup grp = new ButtonGroup();
		
		grp.add(rbSea);
		grp.add(rbLand);
		grp.add(rbMountain);
		
		this.add(rbSea);
		this.add(rbLand);
		this.add(rbMountain);
	}

}
