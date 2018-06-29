package wins.crud;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.alee.laf.WebLookAndFeel;

import app.utils.DAO;
import model.Diploma;
import model.Employee;

public class DiplomaCrud {

	private JFrame frame;

	public JFrame getFrame( ) {
		return frame;
	}

	private JTable table;
	private JTextField tf_ins;
	private JTextField tf_session;
	private JTextField tf_title;
	private JTextField tf_men;

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
		frame.setResizable(false);

		JPanel panel = new JPanel( );
		panel.setBounds(12, 69, 499, 378);
		frame.getContentPane( ).add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane( );
		panel.add(scrollPane, BorderLayout.CENTER);

		tf_men = new JTextField( );
		tf_men.setFont(new Font("Arial", Font.PLAIN, 16));
		tf_men.setColumns(10);
		tf_men.setBounds(388, 33, 114, 24);
		frame.getContentPane( ).add(tf_men);

		tf_ins = new JTextField( );
		tf_ins.setFont(new Font("Arial", Font.PLAIN, 16));
		tf_ins.setBounds(132, 0, 114, 24);
		frame.getContentPane( ).add(tf_ins);
		tf_ins.setColumns(10);

		tf_session = new JTextField( );
		tf_session.setFont(new Font("Arial", Font.PLAIN, 16));
		tf_session.setBounds(378, 0, 114, 24);
		frame.getContentPane( ).add(tf_session);
		tf_session.setColumns(10);

		tf_title = new JTextField( );
		tf_title.setFont(new Font("Arial", Font.PLAIN, 16));
		tf_title.setColumns(10);
		tf_title.setBounds(132, 33, 114, 24);
		frame.getContentPane( ).add(tf_title);

		JLabel label = new JLabel("الشهادة");
		label.setFont(new Font("Arial", Font.BOLD, 15));
		label.setBounds(34, 38, 70, 15);
		frame.getContentPane( ).add(label);

		JLabel lblFrom = new JLabel("تاريخ");
		lblFrom.setFont(new Font("Arial", Font.BOLD, 15));
		lblFrom.setBounds(277, 5, 70, 15);
		frame.getContentPane( ).add(lblFrom);

		JLabel lblNdays = new JLabel("المؤسسة");
		lblNdays.setFont(new Font("Arial", Font.BOLD, 15));
		lblNdays.setBounds(31, 5, 70, 15);
		frame.getContentPane( ).add(lblNdays);

		JLabel lblPeriod = new JLabel("الميزة");
		lblPeriod.setFont(new Font("Arial", Font.BOLD, 15));
		lblPeriod.setBounds(283, 39, 58, 15);
		frame.getContentPane( ).add(lblPeriod);

		JButton btnModify = new JButton("تعديل");
		btnModify.setFont(new Font("Arial", Font.BOLD, 15));
		btnModify.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				int dialogResult = JOptionPane.showConfirmDialog(null, "Sure?");
				if (dialogResult != JOptionPane.YES_OPTION) return;
				Diploma old = getSelectedDiploma(empl, table);
				Diploma d = new Diploma(old.getId( ), tf_title.getText( ),
					tf_ins.getText( ), tf_session.getText( ),
					tf_men.getText( ));
				d.setEmployeeReference(empl.getEmployeeReference( ));
				old.update(d);
				table.setModel(
					Diploma.getDiplomasModel(
						Employee.initEmployee(
							new Employee( ), empl.getEmployeeReference( ))));
				tf_session.setText("");
				tf_ins.setText("");
				tf_title.setText("");
				tf_men.setText("");
			}

		});
		btnModify.setBounds(359, 459, 70, 25);
		frame.getContentPane( ).add(btnModify);

		JButton btnDelete = new JButton("حذف");
		btnDelete.setFont(new Font("Arial", Font.BOLD, 15));
		btnDelete.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				int dialogResult = JOptionPane.showConfirmDialog(null, "Sure?");
				if (dialogResult != JOptionPane.YES_OPTION) return;
				Diploma d = getSelectedDiploma(empl, table);
				d.setEmployeeReference(empl.getEmployeeReference( ));
				d.remove( );
				table.setModel(
					Diploma.getDiplomasModel(
						Employee.initEmployee(
							new Employee( ), empl.getEmployeeReference( ))));
				tf_session.setText("");
				tf_ins.setText("");
				tf_title.setText("");
				tf_men.setText("");
			}
		});
		btnDelete.setBounds(273, 459, 70, 25);
		frame.getContentPane( ).add(btnDelete);

		JButton btnAdd = new JButton("إضافة");
		btnAdd.setFont(new Font("Arial", Font.BOLD, 15));
		btnAdd.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand( ).compareTo("" + "إضافة") == 0) {
					int dialogResult = JOptionPane
									.showConfirmDialog(null, "Sure?");
					if (dialogResult != JOptionPane.YES_OPTION) return;
					int lastid = 0;

					String query = "select id from diploma where refe = '"
									+ empl.getEmployeeReference( )
									+ "' order by id desc";
					System.err.println(query);
					ResultSet r = new DAO( ).exec(query, false);
					try {
						while (r.next( )) {
							lastid = r.getInt("id");
							break;
						}
					} catch (SQLException e1) {
						System.err.println(e1.getMessage( ));
					}

					Diploma d = new Diploma(lastid + 1, tf_title.getText( ),
						tf_ins.getText( ), tf_session.getText( ),
						tf_men.getText( ));
					d.setEmployeeReference(empl.getEmployeeReference( ));
					d.add( );

					table.setModel(
						Diploma.getDiplomasModel(
							Employee.initEmployee(
								new Employee( ),
								empl.getEmployeeReference( ))));
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

		table = new JTable(Diploma.getDiplomasModel(empl));
		table.setFont(new Font("Arial", Font.PLAIN, 15));
		table.addMouseListener(new MouseAdapter( ) {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnAdd.setText("جديد");
				btnModify.setEnabled(true);
				btnDelete.setEnabled(true);
				Diploma r = getSelectedDiploma(empl, table);
				tf_session.setText(r.getSession( ));
				tf_ins.setText(r.getInstitue( ));
				tf_title.setText(r.getTitle( ));
				tf_men.setText(r.getMention( ));
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
		String mention = table.getModel( )
						.getValueAt(table.getSelectedRow( ), 2).toString( );
		int theID = 1;

		String query = "select id from diploma where refe = '"
						+ empl.getEmployeeReference( ) + "' and title = '"
						+ title + "' and sess = '" + session
						+ "' and menstion = '" + mention + "'";
		System.err.println(query);
		ResultSet r = new DAO( ).exec(query, false);

		try {
			while (r.next( )) {
				theID = r.getInt("id");
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage( ));
		}

		Diploma d = new Diploma(theID, title, institue, session, mention);
		d.setEmployeeReference(empl.getEmployeeReference( ));

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
