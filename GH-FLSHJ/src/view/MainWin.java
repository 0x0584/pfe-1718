package view;

import java.awt.EventQueue;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;

import model.Employee;
import model.Employee.Type;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class MainWin {

	private JFrame frame;
	private JTable table;
	private Type type;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable( ) {
			public void run( ) {
				try {
					MainWin window = new MainWin( );
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
	public MainWin() {
		initialize( );
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize( ) {
		type = Type.All;
		frame = new JFrame( );
		frame.setBounds(100, 100, 502, 316);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane( ).setLayout(null);
		try {
			UIManager.setLookAndFeel(
				UIManager.getInstalledLookAndFeels( )[1].getClassName( ));
		} catch (Exception e) {
			// e.printStackTrace( );
			System.err.println(e.getMessage( ));
		}

		JComboBox<String> comboBox = new JComboBox<String>( );
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {
						"شواهد العمل", "إجازة", "بطاقة التنقيط",
						"الشواهد الطبية", "التعويضات"
		}));
		comboBox.setBounds(263, 12, 129, 24);
		frame.getContentPane( ).add(comboBox);

		JButton btnConfirm = new JButton("confirm");
		btnConfirm.setBounds(404, 12, 86, 25);
		frame.getContentPane( ).add(btnConfirm);

		JPanel panel = new JPanel( );
		panel.setBounds(12, 80, 478, 193);
		frame.getContentPane( ).add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		/* add all the employee to the table */
		table = new JTable(Employee.getModelFromXml(Employee.Type.All));

		panel.add(table, BorderLayout.CENTER);

		JCheckBox checkBox = new JCheckBox("موظفين");
		checkBox.setSelected(true);
		checkBox.addChangeListener(new ChangeListener( ) {
			public void stateChanged(ChangeEvent e) {

			}
		});
		checkBox.setBounds(345, 49, 76, 23);
		frame.getContentPane( ).add(checkBox);

		JCheckBox checkBox_1 = new JCheckBox("أساتذة");
		checkBox_1.setSelected(true);
		checkBox_1.setBounds(425, 49, 65, 23);
		frame.getContentPane( ).add(checkBox_1);

		JButton button = new JButton("get info");
		button.setBounds(12, 48, 89, 25);
		frame.getContentPane( ).add(button);
	}
}
