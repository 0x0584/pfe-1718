package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.ButtonGroup;

import java.awt.GridBagLayout;
import javax.swing.JTable;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import java.awt.GridLayout;
import model.Employee.Cadre;
import javax.swing.JSpinner;
import java.awt.SystemColor;
import javax.swing.SpinnerNumberModel;

public class MainWin {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTable table;
	private JTable table_1;
	private JTextField textField_5;

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
		frame = new JFrame( );
		frame.setBounds(100, 100, 597, 692);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane( ).setLayout(null);

		try {
			UIManager.setLookAndFeel(
				UIManager.getInstalledLookAndFeels( )[1].getClassName( ));
		} catch (Exception e) {
			// e.printStackTrace( );
			System.err.println(e.getMessage( ));
		}
		JPanel panel = new JPanel( );
		panel.setBounds(12, 151, 571, 197);
		frame.getContentPane( ).add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setToolTipText("");
		panel.add(tabbedPane);

		JPanel panel_1 = new JPanel( );
		tabbedPane.addTab("Information Personnelle", null, panel_1, null);
		panel_1.setLayout(null);

		JLabel label = new JLabel("First name");
		label.setBounds(25, 15, 84, 15);
		panel_1.add(label);

		JLabel label_1 = new JLabel("Last name");
		label_1.setBounds(25, 45, 84, 15);
		panel_1.add(label_1);

		JLabel label_2 = new JLabel("Birth info");
		label_2.setBounds(25, 75, 84, 15);
		panel_1.add(label_2);

		JLabel label_3 = new JLabel("Married?");
		label_3.setBounds(289, 22, 84, 15);
		panel_1.add(label_3);

		JLabel label_4 = new JLabel("Partner name");
		label_4.setBounds(276, 59, 111, 15);
		panel_1.add(label_4);

		JLabel label_5 = new JLabel("Partner Job");
		label_5.setBounds(289, 96, 84, 15);
		panel_1.add(label_5);

		JLabel lblChildren = new JLabel("Children");
		lblChildren.setBounds(298, 133, 67, 15);
		panel_1.add(lblChildren);

		textField = new JTextField( );
		textField.setColumns(10);
		textField.setBounds(135, 12, 114, 19);
		panel_1.add(textField);

		textField_1 = new JTextField( );
		textField_1.setColumns(10);
		textField_1.setBounds(135, 43, 114, 19);
		panel_1.add(textField_1);

		textField_2 = new JTextField( );
		textField_2.setColumns(10);
		textField_2.setBounds(135, 74, 114, 19);
		panel_1.add(textField_2);

		textField_3 = new JTextField( );
		textField_3.setColumns(10);
		textField_3.setBounds(424, 55, 114, 19);
		panel_1.add(textField_3);

		textField_4 = new JTextField( );
		textField_4.setColumns(10);
		textField_4.setBounds(424, 92, 114, 19);
		panel_1.add(textField_4);

		textField_6 = new JTextField( );
		textField_6.setColumns(10);
		textField_6.setBounds(135, 105, 114, 19);
		panel_1.add(textField_6);

		JLabel label_7 = new JLabel("Address");
		label_7.setBounds(25, 105, 84, 15);
		panel_1.add(label_7);

		textField_7 = new JTextField( );
		textField_7.setColumns(10);
		textField_7.setBounds(135, 136, 114, 19);
		panel_1.add(textField_7);

		JLabel label_8 = new JLabel("Phone number");
		label_8.setBounds(12, 135, 111, 15);
		panel_1.add(label_8);

		JRadioButton radioButton = new JRadioButton("Yes");
		radioButton.setSelected(true);
		radioButton.setBounds(424, 18, 61, 19);
		panel_1.add(radioButton);

		JRadioButton radioButton_1 = new JRadioButton("No");
		radioButton_1.setBounds(490, 18, 61, 19);
		panel_1.add(radioButton_1);

		ButtonGroup bg = new ButtonGroup( );
		bg.add(radioButton);
		bg.add(radioButton_1);
		JSpinner spinner = new JSpinner( );
		spinner.setModel(
			new SpinnerNumberModel(new Short((short) 0), new Short((short) 0),
				new Short((short) 10), new Short((short) 1)));
		spinner.setBounds(424, 129, 114, 20);
		panel_1.add(spinner);

		JPanel panel_2 = new JPanel( );
		tabbedPane.addTab("Administrative Situtation", null, panel_2, null);
		panel_2.setLayout(null);

		JLabel lblCadre = new JLabel("Cadre");
		lblCadre.setBounds(41, 15, 53, 15);
		panel_2.add(lblCadre);

		JLabel lblReference_1 = new JLabel("Financial ref");
		lblReference_1.setBounds(19, 45, 97, 15);
		panel_2.add(lblReference_1);

		JLabel lblRentReference = new JLabel("Rent ref");
		lblRentReference.setBounds(25, 75, 84, 15);
		panel_2.add(lblRentReference);

		JLabel lblEmployment = new JLabel("Employment");
		lblEmployment.setBounds(305, 22, 111, 15);
		panel_2.add(lblEmployment);

		JLabel lblJoined = new JLabel("Joined");
		lblJoined.setBounds(318, 59, 84, 15);
		panel_2.add(lblJoined);

		JLabel lblReason = new JLabel("Reason");
		lblReason.setBounds(318, 96, 84, 15);
		panel_2.add(lblReason);

		textField_9 = new JTextField( );
		textField_9.setColumns(10);
		textField_9.setBounds(151, 46, 114, 19);
		panel_2.add(textField_9);

		textField_10 = new JTextField( );
		textField_10.setColumns(10);
		textField_10.setBounds(151, 76, 114, 19);
		panel_2.add(textField_10);

		textField_11 = new JTextField( );
		textField_11.setColumns(10);
		textField_11.setBounds(422, 17, 114, 19);
		panel_2.add(textField_11);

		textField_12 = new JTextField( );
		textField_12.setColumns(10);
		textField_12.setBounds(422, 53, 114, 19);
		panel_2.add(textField_12);

		textField_14 = new JTextField( );
		textField_14.setColumns(10);
		textField_14.setBounds(422, 130, 114, 19);
		panel_2.add(textField_14);

		JLabel lblMission = new JLabel("Mission");
		lblMission.setBounds(318, 133, 84, 15);
		panel_2.add(lblMission);

		textField_15 = new JTextField( );
		textField_15.setColumns(10);
		textField_15.setBounds(151, 106, 114, 19);
		panel_2.add(textField_15);

		JLabel lblPreviousJob = new JLabel("Previous job ");
		lblPreviousJob.setBounds(12, 105, 111, 15);
		panel_2.add(lblPreviousJob);

		JComboBox<Cadre> comboBox = new JComboBox<Cadre>( );
		comboBox.setModel(new DefaultComboBoxModel<Cadre>(Cadre.values( )));
		comboBox.setBounds(151, 11, 114, 24);
		panel_2.add(comboBox);

		JComboBox<String> comboBox_1 = new JComboBox<String>( );
		comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {
						"Transfer", "Exam"
		}));
		comboBox_1.setBounds(422, 89, 114, 24);
		panel_2.add(comboBox_1);

		JLabel lblCurrentJob = new JLabel("Current job");
		lblCurrentJob.setBounds(12, 135, 111, 15);
		panel_2.add(lblCurrentJob);

		textField_5 = new JTextField( );
		textField_5.setColumns(10);
		textField_5.setBounds(151, 136, 114, 19);
		panel_2.add(textField_5);

		JPanel panel_3 = new JPanel( );
		panel_3.setBounds(12, 12, 571, 127);
		frame.getContentPane( ).add(panel_3);
		panel_3.setLayout(null);

		JLabel lblMrRchidAnas = new JLabel("Mr. Rchid Anas, 23 years old");
		lblMrRchidAnas.setBounds(290, 12, 269, 15);
		panel_3.add(lblMrRchidAnas);

		JLabel lblSingle = new JLabel("Grade 9, 3 diplomes");
		lblSingle.setBounds(290, 25, 162, 15);
		panel_3.add(lblSingle);

		JLabel lblDiplomas = new JLabel("Notes:");
		lblDiplomas.setBounds(134, 66, 100, 15);
		panel_3.add(lblDiplomas);

		JPanel panel_4 = new JPanel( );
		panel_4.setBackground(SystemColor.activeCaption);
		panel_4.setBounds(12, 12, 104, 103);
		panel_3.add(panel_4);
		panel_4.setLayout(null);

		JLabel lblNewLabel = new JLabel("(Image)");
		lblNewLabel.setForeground(SystemColor.text);
		lblNewLabel.setBackground(SystemColor.controlLtHighlight);
		lblNewLabel.setBounds(17, 12, 70, 15);
		panel_4.add(lblNewLabel);

		JTextPane textPane = new JTextPane( );
		textPane.setBounds(205, 66, 354, 49);
		panel_3.add(textPane);

		JLabel lblM = new JLabel("CIN: M550630");
		lblM.setBounds(134, 12, 100, 15);
		panel_3.add(lblM);

		JLabel lblRef = new JLabel("ref: 15462021");
		lblRef.setBounds(134, 25, 100, 15);
		panel_3.add(lblRef);

		JPanel panel_5 = new JPanel( );
		panel_5.setLayout(null);
		panel_5.setBounds(12, 360, 571, 136);
		frame.getContentPane( ).add(panel_5);

		String columnNames[][] = {
						{
										"Diploma", "Second", "ThirdCol",
										"FourthCol"
						}, { }
		};
		DefaultTableModel dataModel0 = new DefaultTableModel( );
		for (int col = 0; col < columnNames.length; col++) {
			dataModel0.addColumn(columnNames[col]);
		}
		table = new JTable(dataModel0);
		table.setBounds(0, 24, 571, 112);
		panel_5.add(table);

		JLabel label_18 = new JLabel("Diplomas");
		label_18.setBounds(250, 0, 70, 15);
		panel_5.add(label_18);

		JPanel panel_6 = new JPanel( );
		panel_6.setLayout(null);
		panel_6.setBounds(12, 508, 571, 136);
		frame.getContentPane( ).add(panel_6);

		table_1 = new JTable( );
		table_1.setBounds(0, 24, 571, 112);
		panel_6.add(table_1);

		JLabel lblUplifts = new JLabel("Uplifts");
		lblUplifts.setBounds(250, 0, 70, 15);
		panel_6.add(lblUplifts);
	}
}
