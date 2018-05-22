package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.alee.laf.WebLookAndFeel;

import model.Employee;
import operation.Printer;
import operation.Type;
import xml.XmlFile;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import operation.SearchField;

public class MainWin {

	private JFrame frame;

	public JFrame getFrame( ) {
		return frame;
	}

	private JTable table;
	private Type type;
	private JTextField tf_search;

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
		WebLookAndFeel.install( );
		type = Type.All;

		frame = new JFrame( );
		frame.setBounds(100, 100, 632, 430);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane( ).setLayout(null);

		JPanel panel = new JPanel( );
		panel.setBounds(12, 47, 608, 305);
		frame.getContentPane( ).add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		// add all the employee to the table
		table = new JTable(XmlFile.getModel(type));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setupJTable(table);

		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane, BorderLayout.CENTER);

		JCheckBox profchk = new JCheckBox("أساتذة");
		profchk.setSelected(true);
		profchk.setBounds(555, 365, 65, 23);

		JCheckBox emplchk = new JCheckBox("موظفين");
		emplchk.setSelected(true);
		emplchk.setBounds(475, 365, 76, 23);

		emplchk.addChangeListener(new ChangeListener( ) {
			public void stateChanged(ChangeEvent e) {
				boolean a = profchk.isSelected( );
				boolean b = emplchk.isSelected( );
				type = Type.filter(a, b);
				table.setModel(XmlFile.getModel(type));
				setupJTable(table);
			}
		});

		profchk.addChangeListener(new ChangeListener( ) {
			public void stateChanged(ChangeEvent e) {
				boolean a = profchk.isSelected( );
				boolean b = emplchk.isSelected( );
				type = Type.filter(a, b);
				table.setModel(XmlFile.getModel(type));
				setupJTable(table);
			}
		});

		frame.getContentPane( ).add(emplchk);
		frame.getContentPane( ).add(profchk);

		JButton button = new JButton("السجل الكامل");
		button.addActionListener(new ActionListener( ) {
			// this would create a new `Employee` based on the selected row and
			// create a new `InfoWin` to show all of it's info
			public void actionPerformed(ActionEvent e) {
				new InfoWin(new Employee(
					table.getModel( ).getValueAt(table.getSelectedRow( ), 0)
									.toString( ))).getFrame( ).setVisible(true);
			}
		});
		button.setBounds(12, 364, 129, 25);
		frame.getContentPane( ).add(button);

		JCheckBox chckbx0 = new JCheckBox("التعويضات");
		chckbx0.setBounds(12, 13, 88, 23);
		frame.getContentPane( ).add(chckbx0);

		JCheckBox chckbx1 = new JCheckBox("شهادة العمل");
		chckbx1.setBounds(104, 13, 102, 23);
		frame.getContentPane( ).add(chckbx1);

		JCheckBox chckbx2 = new JCheckBox("إجازة");
		chckbx2.setBounds(210, 13, 65, 23);
		frame.getContentPane( ).add(chckbx2);

		JCheckBox chckbx3 = new JCheckBox("شهادة طبية");
		chckbx3.setBounds(279, 13, 95, 23);
		frame.getContentPane( ).add(chckbx3);

		JCheckBox chckbx4 = new JCheckBox("بطاقة التنقيط");
		chckbx4.setBounds(378, 13, 112, 23);
		frame.getContentPane( ).add(chckbx4);

		JButton btnConfirm = new JButton("إستخراج");
		btnConfirm.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				// TODO: finish this handle all of this
				// TODO: distinguish between employee and professor

				if (chckbx1.isSelected( )) {
					AttTravailView window = new AttTravailView(new Employee(
						table.getModel( ).getValueAt(table.getSelectedRow( ), 0)
										.toString( )));
					window.getFrame( ).setVisible(true);
					Printer.doPrint(window.getFrame( ));
				}

			}
		});
		btnConfirm.setBounds(534, 12, 86, 25);
		frame.getContentPane( ).add(btnConfirm);

		JComboBox<SearchField> comboFields = new JComboBox<SearchField>( );
		comboFields.setModel(
			new DefaultComboBoxModel<SearchField>(SearchField.values( )));
		comboFields.setBounds(386, 364, 81, 24);
		frame.getContentPane( ).add(comboFields);

		tf_search = new JTextField( );
		tf_search.addKeyListener(new KeyAdapter( ) {
			@Override
			public void keyReleased(KeyEvent e) {
				SearchField searchfield;
				String text = tf_search.getText( ).trim( );

				switch (e.getKeyCode( )) {
				case KeyEvent.VK_ALPHANUMERIC:
					text.concat("" + e.getKeyChar( ));
					break;
				case KeyEvent.VK_DELETE:
					text = text.substring(0, text.length( ) - 1);
					break;
				}

				searchfield = (SearchField) comboFields.getSelectedItem( );
				table.setModel(XmlFile.getModel(text, searchfield, type));
				setupJTable(table);
			}
		});
		tf_search.setBounds(153, 364, 221, 25);
		frame.getContentPane( ).add(tf_search);
		tf_search.setColumns(10);

	}

	private void setupJTable(JTable table) {
		if (table.getRowCount( ) != 0) {
			// just in case
			table.clearSelection( );
			// select first line
			table.addRowSelectionInterval(0, 0);
		}
	}
}
