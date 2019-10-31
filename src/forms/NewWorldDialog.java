package forms;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class NewWorldDialog extends JDialog {
	
	private static final long serialVersionUID = -4959682778676542107L;

	public boolean Result = false;
	
	public JTextField txtSizeX;
	public JTextField txtSizeY;

	public NewWorldDialog() {
		setSize(200,200);
		
		JLabel lblSizeX = new JLabel("Size X");
		JLabel lblSizeY = new JLabel("Size Y");
		
		txtSizeX = new JTextField("25");
		txtSizeY = new JTextField("25");
		
		JButton btnCancel = new JButton("Abbrechen");
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				closeForm();
			}
		});
		
		JButton btnCreate = new JButton("Erstellen");
		btnCreate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Result = true;
				closeForm();
			}
		});
		
		getContentPane().setLayout(new GridLayout(3, 2));
		
		getContentPane().add(lblSizeX);
		getContentPane().add(txtSizeX);
		getContentPane().add(lblSizeY);
		getContentPane().add(txtSizeY);
		getContentPane().add(btnCancel);
		getContentPane().add(btnCreate);
		
		// TODO Auto-generated constructor stub
	}
	
	private void closeForm() {
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}

}
