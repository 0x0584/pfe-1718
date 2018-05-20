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
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JTextPane;

public class MainWin {

	private JFrame frame;

	public JFrame getFrame( ) {
		return frame;
	}

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
		frame.setBounds(100, 100, 632, 569);
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
		comboBox.setBounds(393, 12, 129, 24);
		frame.getContentPane( ).add(comboBox);

		JButton btnConfirm = new JButton("confirm");
		btnConfirm.setBounds(534, 12, 86, 25);
		frame.getContentPane( ).add(btnConfirm);

		JPanel panel = new JPanel( );
		panel.setBounds(16, 185, 608, 305);
		frame.getContentPane( ).add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		/* add all the employee to the table */
		// table = new JTable(Employee.getModelFromXml(type));
		/*
		 * panel.add(table, BorderLayout.CENTER);
		 */
		table = new JTable(Employee.getModelFromXml(type));

		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane, BorderLayout.CENTER);

		JCheckBox profchk = new JCheckBox("أساتذة");
		profchk.setSelected(true);
		profchk.setBounds(559, 503, 65, 23);

		JCheckBox emplchk = new JCheckBox("موظفين");
		emplchk.setSelected(true);
		emplchk.setBounds(479, 503, 76, 23);

		emplchk.addChangeListener(new ChangeListener( ) {
			public void stateChanged(ChangeEvent e) {
				boolean a = profchk.isSelected( );
				boolean b = emplchk.isSelected( );
				type = Type.filter(a, b);
				table.setModel(Employee.getModelFromXml(type));
			}
		});

		profchk.addChangeListener(new ChangeListener( ) {
			public void stateChanged(ChangeEvent e) {
				boolean a = profchk.isSelected( );
				boolean b = emplchk.isSelected( );
				type = Type.filter(a, b);
				table.setModel(Employee.getModelFromXml(type));
			}
		});

		frame.getContentPane( ).add(emplchk);
		frame.getContentPane( ).add(profchk);

		JButton button = new JButton("السجل الكامل");
		button.setBounds(16, 502, 129, 25);
		frame.getContentPane( ).add(button);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(16, 46, 604, 127);
		frame.getContentPane().add(panel_1);
		
		JLabel lblsss = new JLabel("السيد%s %s، %s سنة");
		lblsss.setBounds(439, 12, 139, 15);
		panel_1.add(lblsss);
		
		JLabel lblss = new JLabel("السلم %s/الرتبة %s");
		lblss.setBounds(449, 25, 117, 15);
		panel_1.add(lblss);
		
		JLabel label_2 = new JLabel("الملاحظات:");
		label_2.setBounds(312, 39, 76, 15);
		panel_1.add(label_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(SystemColor.activeCaption);
		panel_2.setBounds(12, 12, 104, 103);
		panel_1.add(panel_2);
		
		JLabel label_3 = new JLabel("(Image)");
		label_3.setForeground(Color.WHITE);
		label_3.setBackground(Color.WHITE);
		label_3.setBounds(17, 12, 70, 15);
		panel_2.add(label_3);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBounds(153, 66, 425, 49);
		panel_1.add(textPane);
		
		JLabel lbls = new JLabel("ب.ت.و.: %s");
		lbls.setBounds(153, 12, 123, 15);
		panel_1.add(lbls);
		
		JLabel lbls_1 = new JLabel("ر.ت: %s");
		lbls_1.setBounds(153, 25, 100, 15);
		panel_1.add(lbls_1);
	}
}
