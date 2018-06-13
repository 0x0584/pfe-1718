package wins.crud;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.alee.laf.WebLookAndFeel;

import app.Mention;
import app.utils.XmlFile;
import model.Diploma;
import model.Employee;
import model.Modeling;

public class DiplomaCrud {

	private JFrame frame;

	public JFrame getFrame( ) {
		return frame;
	}

	private JTable table;
	private JTextField tf_ins;
	private JTextField tf_session;
	private JTextField tf_title;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable( ) {
			public void run( ) {
				try {
					DiplomaCrud window = new DiplomaCrud(new Employee( ));
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace( );
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @param employee
	 */
	public DiplomaCrud(Employee empl) {
		initialize(empl);
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @param empl
	 */
	private void initialize(Employee empl) {
		WebLookAndFeel.install( );

		frame = new JFrame( );
		frame.setBounds(100, 100, 526, 519);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane( ).setLayout(null);

		JPanel panel = new JPanel( );
		panel.setBounds(12, 69, 499, 378);
		frame.getContentPane( ).add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane( );
		panel.add(scrollPane, BorderLayout.CENTER);

		tf_ins = new JTextField( );
		tf_ins.setBounds(132, 0, 114, 24);
		frame.getContentPane( ).add(tf_ins);
		tf_ins.setColumns(10);

		tf_session = new JTextField( );
		tf_session.setBounds(378, 0, 114, 24);
		frame.getContentPane( ).add(tf_session);
		tf_session.setColumns(10);

		tf_title = new JTextField( );
		tf_title.setColumns(10);
		tf_title.setBounds(132, 33, 114, 24);
		frame.getContentPane( ).add(tf_title);

		JLabel label = new JLabel("الشهادة");
		label.setBounds(34, 38, 70, 15);
		frame.getContentPane( ).add(label);

		JComboBox<Mention> comboMen = new JComboBox<Mention>( );
		comboMen.setModel(new DefaultComboBoxModel<Mention>(Mention.values( )));
		comboMen.setBounds(378, 33, 114, 24);
		frame.getContentPane( ).add(comboMen);

		JLabel lblFrom = new JLabel("تاريخ");
		lblFrom.setBounds(277, 5, 70, 15);
		frame.getContentPane( ).add(lblFrom);

		JLabel lblNdays = new JLabel("المؤسسة");
		lblNdays.setBounds(31, 5, 70, 15);
		frame.getContentPane( ).add(lblNdays);

		JLabel lblPeriod = new JLabel("الميزة");
		lblPeriod.setBounds(283, 39, 58, 15);
		frame.getContentPane( ).add(lblPeriod);

		JButton btnModify = new JButton("تعديل");
		btnModify.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				Diploma old = getSelectedDiploma(empl, table);
				Diploma d = new Diploma(old.getId( ), tf_title.getText( ),
					tf_ins.getText( ), tf_session.getText( ),
					Mention.parseMention(
						comboMen.getSelectedItem( ).toString( )));
				old.update(d);
				table.setModel(
					Modeling.getDiplomasModel(
						XmlFile.initEmployee(
							new Employee( ), empl.getReference( ))));
				tf_session.setText("");
				tf_ins.setText("");
			}

		});
		btnModify.setBounds(359, 459, 70, 25);
		frame.getContentPane( ).add(btnModify);

		JButton btnDelete = new JButton("حذف");
		btnDelete.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				Diploma d = getSelectedDiploma(empl, table);
				d.remove( );
				table.setModel(
					Modeling.getDiplomasModel(
						XmlFile.initEmployee(
							new Employee( ), empl.getReference( ))));
				tf_session.setText("");
				tf_ins.setText("");
			}
		});
		btnDelete.setBounds(273, 459, 70, 25);
		frame.getContentPane( ).add(btnDelete);

		JButton btnAdd = new JButton("إضافة");
		btnAdd.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand( ).compareTo("" + "إضافة") == 0) {
					Diploma d = new Diploma(
						XmlFile.getLastDiplomaId(empl.getElement( )) + 1,
						tf_title.getText( ), tf_ins.getText( ),
						tf_session.getText( ), Mention.parseMention(
							comboMen.getSelectedItem( ).toString( )));
					d.add( );

					table.setModel(
						Modeling.getDiplomasModel(
							XmlFile.initEmployee(
								new Employee( ), empl.getReference( ))));
					setupJTable(table);
					btnAdd.setText("جديد");
					btnModify.setEnabled(true);
					btnDelete.setEnabled(true);
				} else {
					tf_session.setText("");
					tf_ins.setText("");
					btnAdd.setText("إضافة");
					btnModify.setEnabled(!true);
					btnDelete.setEnabled(!true);
				}
			}
		});
		btnAdd.setBounds(441, 459, 70, 25);
		frame.getContentPane( ).add(btnAdd);

		table = new JTable(Modeling.getDiplomasModel(empl));
		table.addMouseListener(new MouseAdapter( ) {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnAdd.setText("جديد");
				btnModify.setEnabled(true);
				btnDelete.setEnabled(true);
				Diploma r = getSelectedDiploma(empl, table);
				// TODO: update text fields
				tf_session.setText(r.getSession( ));
				tf_ins.setText(r.getInstitue( ));
				tf_title.setText(r.getTitle( ));
				comboMen.setSelectedIndex(r.getMention( ).ordinal( ));
			}
		});
		/*
		 * new TableCellListener(table, new AbstractAction( ) { private static
		 * final long serialVersionUID = 3336978815956326448L;
		 * 
		 * @Override public void actionPerformed(ActionEvent e) {
		 * TableCellListener tcl = (TableCellListener) e.getSource( ); JTable
		 * table = tcl.getTable( ); } });
		 */
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setupJTable(table);
		scrollPane.setViewportView(table);

	}

	private Diploma getSelectedDiploma(Employee empl, JTable table) {
		String title = table.getModel( ).getValueAt(table.getSelectedRow( ), 3)
						.toString( );
		String institue = table.getModel( )
						.getValueAt(table.getSelectedRow( ), 1).toString( );
		String session = table.getModel( )
						.getValueAt(table.getSelectedRow( ), 0).toString( );
		Mention mention = Mention.parseMention(
			table.getModel( ).getValueAt(table.getSelectedRow( ), 2)
							.toString( ));
		int theID = XmlFile.getDiplomaId(
			empl.getElement( ), table.getSelectedRow( ));
		Diploma d = new Diploma(theID, title, institue, session, mention);
		d.setEmployeeRefrence(empl.getReference( ));
		return d;
	}

	private void setupJTable(JTable table) {
		if (table.getRowCount( ) != 0) {
			// just in case
			table.clearSelection( );
			// select first line
			// table.addRowSelectionInterval(0, 0);
		}
	}
}
