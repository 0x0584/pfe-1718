package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JTextPane;

public class CongeWin {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable( ) {
			public void run( ) {
				try {
					CongeWin window = new CongeWin( );
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace( );
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CongeWin() {
		initialize( );
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize( ) {
		frame = new JFrame( );
		frame.setBounds(100, 100, 594, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(12, 12, 571, 127);
		frame.getContentPane().add(panel);
		
		JLabel label = new JLabel("السيد أنس ارشيد، 23 سنة");
		label.setBounds(397, 12, 162, 15);
		panel.add(label);
		
		JLabel label_1 = new JLabel("السلم 9/الرتبة 4");
		label_1.setBounds(455, 25, 104, 15);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("الملاحظات:");
		label_2.setBounds(293, 39, 76, 15);
		panel.add(label_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setBounds(12, 12, 104, 103);
		panel.add(panel_1);
		
		JLabel label_3 = new JLabel("(Image)");
		label_3.setForeground(Color.WHITE);
		label_3.setBackground(Color.WHITE);
		label_3.setBounds(17, 12, 70, 15);
		panel_1.add(label_3);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBounds(134, 66, 425, 49);
		panel.add(textPane);
		
		JLabel label_4 = new JLabel("ب.ت.و.: M550630");
		label_4.setBounds(134, 12, 123, 15);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("ر.ت: 15462021");
		label_5.setBounds(134, 25, 100, 15);
		panel.add(label_5);
	}
}
