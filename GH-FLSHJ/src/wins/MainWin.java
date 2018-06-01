package wins;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.alee.laf.WebLookAndFeel;

import app.SearchField;
import app.utils.DateUtils;
import app.EmployeeType;
import model.Employee;
import model.Modeling;
import views.AttTravailView;
import views.HolidayAdminiView;
import views.HolidayExcepView;
import views.HolidayToQuitView;
import views.NotationView;
import app.Holiday;
import app.Period;

import javax.swing.JLabel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class MainWin {

	private JFrame frame;
	private final static int REF_INDEX = 0;

	public JFrame getFrame( ) {
		return frame;
	}

	private final static int WIDTH = 632, HEIGHT = 395, EXTRA_HEIGHT = 450;
	private JTable table;
	private EmployeeType type;
	private JTextField tf_search;
	private JTextField tf_to;
	private JTextField tf_from;
	private String raison;
	private Date fromDate, toDate;
	private JTextField tf_raison;

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
		type = EmployeeType.All;
		raison = "";
		fromDate = new Date( );
		toDate = DateUtils.add(Period.ONE_DAY, new Date( ), 1);

		frame = new JFrame( );
		frame.setBounds(100, 100, 632, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane( ).setLayout(null);

		JPanel panel = new JPanel( );
		panel.setBounds(12, 47, 608, 240);
		frame.getContentPane( ).add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		// add all the employee to the table
		table = new JTable(Modeling.getDefaultModel(type));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setupJTable(table);

		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane, BorderLayout.CENTER);

		JCheckBox profchk = new JCheckBox("أساتذة");
		profchk.setSelected(true);
		profchk.setBounds(475, 332, 65, 23);

		JCheckBox emplchk = new JCheckBox("موظفين");
		emplchk.setSelected(true);
		emplchk.setBounds(544, 332, 76, 23);

		emplchk.addChangeListener(new ChangeListener( ) {
			public void stateChanged(ChangeEvent e) {
				boolean a = profchk.isSelected( );
				boolean b = emplchk.isSelected( );
				type = EmployeeType.filter(a, b);
				table.setModel(Modeling.getDefaultModel(type));
				setupJTable(table);
			}
		});

		profchk.addChangeListener(new ChangeListener( ) {
			public void stateChanged(ChangeEvent e) {
				boolean a = profchk.isSelected( );
				boolean b = emplchk.isSelected( );
				type = EmployeeType.filter(a, b);
				table.setModel(Modeling.getDefaultModel(type));
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
		button.setBounds(12, 299, 129, 25);
		frame.getContentPane( ).add(button);

		JCheckBox chckbx1 = new JCheckBox("شهادة العمل");
		chckbx1.setBounds(12, 12, 102, 23);
		frame.getContentPane( ).add(chckbx1);

		JCheckBox chckbx2 = new JCheckBox("إجازة");
		chckbx2.addChangeListener(new ChangeListener( ) {
			public void stateChanged(ChangeEvent e) {
				frame.setSize(
					WIDTH, chckbx2.isSelected( ) ? EXTRA_HEIGHT : HEIGHT);
			}
		});
		chckbx2.setBounds(128, 12, 65, 23);
		frame.getContentPane( ).add(chckbx2);

		JCheckBox chckbx4 = new JCheckBox("بطاقة التنقيط");
		chckbx4.setBounds(197, 12, 112, 23);
		frame.getContentPane( ).add(chckbx4);

		JComboBox<SearchField> comboFields = new JComboBox<SearchField>( );
		comboFields.setModel(
			new DefaultComboBoxModel<SearchField>(SearchField.values( )));
		comboFields.setBounds(539, 11, 81, 24);
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
				table.setModel(
					Modeling.getDefaultModel(text, searchfield, type));
				setupJTable(table);
			}
		});
		tf_search.setBounds(317, 11, 211, 25);
		frame.getContentPane( ).add(tf_search);
		tf_search.setColumns(10);

		tf_raison = new JTextField( );
		tf_raison.addKeyListener(new KeyAdapter( ) {
			@Override
			public void keyReleased(KeyEvent e) {

				String text = tf_raison.getText( ).trim( );

				switch (e.getKeyCode( )) {
				case KeyEvent.VK_ALPHANUMERIC:
					text.concat("" + e.getKeyChar( ));
					break;
				case KeyEvent.VK_DELETE:
					text = text.substring(0, text.length( ) - 1);
					break;
				}

				raison = text;
			}
		});
		tf_raison.setBounds(161, 389, 148, 25);
		frame.getContentPane( ).add(tf_raison);
		tf_raison.setColumns(10);
		tf_raison.setVisible(false);
		JComboBox<String> defaultHolidays = new JComboBox<String>( );
		defaultHolidays.addItemListener(new ItemListener( ) {
			public void itemStateChanged(ItemEvent e) {
				tf_raison.setText(
					defaultHolidays.getSelectedItem( ).toString( ));
				raison = defaultHolidays.getSelectedItem( ).toString( );
			}
		});
		defaultHolidays.setModel(new DefaultComboBoxModel<String>(new String[] {
						"Semester I", "Semester II", "Ete"
		}));
		defaultHolidays.setBounds(161, 389, 148, 24);
		frame.getContentPane( ).add(defaultHolidays);
		defaultHolidays.setVisible(true);

		raison = defaultHolidays.getSelectedItem( ).toString( );

		JComboBox<Holiday> holidayTypes = new JComboBox<Holiday>( );
		holidayTypes.addItemListener(new ItemListener( ) {
			public void itemStateChanged(ItemEvent e) {
				if (e.getItem( ) == Holiday.NORMAL) {
					defaultHolidays.setVisible(true);
					tf_raison.setVisible(false);
					tf_raison.setText(
						defaultHolidays.getSelectedItem( ).toString( ));
				} else if (e.getItem( ) == Holiday.EXCEP) {
					defaultHolidays.setVisible(false);
					tf_raison.setVisible(true);
				} else if (e.getItem( ) == Holiday.TO_QUIT) {
					defaultHolidays.setVisible(false);
					tf_raison.setVisible(false);
					tf_raison.setText("");
				}
			}
		});
		holidayTypes.setModel(
			new DefaultComboBoxModel<Holiday>(Holiday.values( )));
		holidayTypes.setBounds(12, 389, 141, 24);
		frame.getContentPane( ).add(holidayTypes);

		tf_to = new JTextField(DateUtils.parseDate(toDate));
		tf_to.setBounds(509, 389, 114, 25);
		tf_to.addKeyListener(new KeyAdapter( ) {
			@Override
			public void keyReleased(KeyEvent e) {
				String text = tf_to.getText( ).trim( );

				switch (e.getKeyCode( )) {
				case KeyEvent.VK_ALPHANUMERIC:
					text.concat("" + e.getKeyChar( ));
					break;
				case KeyEvent.VK_DELETE:
					text = text.substring(0, text.length( ) - 1);
					break;
				}

				toDate = DateUtils.parseDate(text);
			}
		});
		frame.getContentPane( ).add(tf_to);
		tf_to.setColumns(10);

		tf_from = new JTextField(DateUtils.parseDate(fromDate));
		tf_from.setBounds(352, 389, 114, 25);
		tf_from.addKeyListener(new KeyAdapter( ) {
			@Override
			public void keyReleased(KeyEvent e) {
				String text = tf_from.getText( ).trim( );

				switch (e.getKeyCode( )) {
				case KeyEvent.VK_ALPHANUMERIC:
					text.concat("" + e.getKeyChar( ));
					break;
				case KeyEvent.VK_DELETE:
					text = text.substring(0, text.length( ) - 1);
					break;
				}

				fromDate = DateUtils.parseDate(text);
			}
		});
		frame.getContentPane( ).add(tf_from);
		tf_from.setColumns(10);

		JLabel lblDe = new JLabel("A");
		lblDe.setBounds(474, 394, 27, 15);
		frame.getContentPane( ).add(lblDe);

		JLabel lblDe_1 = new JLabel("De");
		lblDe_1.setBounds(317, 394, 27, 15);
		frame.getContentPane( ).add(lblDe_1);

		JLabel lblerr = new JLabel("Option de conge");
		lblerr.setBounds(22, 367, 364, 15);
		frame.getContentPane( ).add(lblerr);

		JButton btnConfirm = new JButton("إستخراج");
		btnConfirm.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				// TODO: handle all of this

				if (chckbx1.isSelected( )) {
					AttTravailView window = new AttTravailView(
						getSelectedEmployee(table));
					window.getFrame( ).setVisible(true);
				}

				if (chckbx2.isSelected( )) {
					if (fromDate != null && toDate != null) {
						lblerr.setText("Option de conge");
						if (cmp(Holiday.TO_QUIT, holidayTypes)) {
							HolidayToQuitView window = new HolidayToQuitView(
								getSelectedEmployee(table), raison, fromDate,
								toDate);
							window.getFrame( ).setVisible(true);
						} else if (cmp(Holiday.EXCEP, holidayTypes)) {
							HolidayExcepView window = new HolidayExcepView(
								getSelectedEmployee(table), raison, fromDate,
								toDate);
							window.getFrame( ).setVisible(true);
						} else if (cmp(Holiday.NORMAL, holidayTypes)) {
							HolidayAdminiView window = new HolidayAdminiView(
								getSelectedEmployee(table), raison, fromDate,
								toDate);
							window.getFrame( ).setVisible(true);
						}
					} else {
						lblerr.setText("DATE FORMAT ERR: example: 1994-09-15");
					}
				}

				if (chckbx4.isSelected( )) {
					NotationView window = new NotationView(
						getSelectedEmployee(table));
					window.getFrame( ).setVisible(true);
				}

			}

			private boolean cmp(Holiday h, JComboBox<Holiday> c) {
				return h.toString( ).compareTo(
					c.getSelectedItem( ).toString( )) == 0;
			}

		});
		btnConfirm.setBounds(534, 299, 86, 25);
		frame.getContentPane( ).add(btnConfirm);

		JButton button_1 = new JButton("التعويضات");
		button_1.setBounds(153, 299, 129, 25);
		frame.getContentPane( ).add(button_1);

		JButton button_2 = new JButton("شواهد طبية");
		button_2.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				new MedicalWin(getSelectedEmployee(table)).getFrame( )
								.setVisible(true);
			}
		});
		button_2.setBounds(294, 299, 117, 25);
		frame.getContentPane( ).add(button_2);

		JButton button_3 = new JButton("*");
		button_3.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				// refresh
				table.setModel(Modeling.getDefaultModel(type));
			}
		});
		button_3.setBounds(488, 299, 40, 25);
		frame.getContentPane( ).add(button_3);
	}

	private void setupJTable(JTable table) {
		if (table.getRowCount( ) != 0) {
			// just in case
			table.clearSelection( );
			// select first line
			table.addRowSelectionInterval(0, 0);
		}
	}

	private Employee getSelectedEmployee(JTable table) {
		return new Employee(
			table.getModel( ).getValueAt(table.getSelectedRow( ), REF_INDEX)
							.toString( ));
	}
}
