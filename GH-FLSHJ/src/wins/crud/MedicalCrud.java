package wins.crud;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

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
import app.utils.DateUtil;
import model.Employee;
import model.MedicalCertif;
import java.awt.Font;

public class MedicalCrud {

	private JFrame frame;

	public JFrame getFrame( ) {
		return frame;
	}

	private JTable table;
	private JTextField tf_ndays;
	private JTextField tf_from;
	private JTextField tf_s;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable( ) {
			public void run( ) {
				try {
					MedicalCrud window = new MedicalCrud(new Employee( ));
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
	public MedicalCrud(Employee empl) {
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
		frame.setBounds(100, 100, 553, 431);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane( ).setLayout(null);
		frame.setResizable(false);

		JPanel panel = new JPanel( );
		panel.setBounds(13, 38, 528, 317);
		frame.getContentPane( ).add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane( );
		panel.add(scrollPane, BorderLayout.CENTER);

		tf_s = new JTextField( );
		tf_s.setFont(new Font("Arial", Font.PLAIN, 15));
		tf_s.setColumns(10);
		tf_s.setBounds(432, 2, 114, 24);
		frame.getContentPane( ).add(tf_s);

		tf_ndays = new JTextField( );
		tf_ndays.setFont(new Font("Arial", Font.PLAIN, 15));
		tf_ndays.setBounds(64, 0, 114, 24);
		frame.getContentPane( ).add(tf_ndays);
		tf_ndays.setColumns(10);

		tf_from = new JTextField( );
		tf_from.setFont(new Font("Arial", Font.PLAIN, 15));
		tf_from.setBounds(254, 0, 114, 24);
		frame.getContentPane( ).add(tf_from);
		tf_from.setColumns(10);

		JLabel lblFrom = new JLabel("تاريخ البدئ");
		lblFrom.setFont(new Font("Arial", Font.BOLD, 15));
		lblFrom.setBounds(181, 5, 70, 15);
		frame.getContentPane( ).add(lblFrom);

		JLabel lblNdays = new JLabel("عدد الأيام");
		lblNdays.setFont(new Font("Arial", Font.BOLD, 15));
		lblNdays.setBounds(3, 5, 58, 15);
		frame.getContentPane( ).add(lblNdays);

		JLabel lblPeriod = new JLabel("الأسدس");
		lblPeriod.setFont(new Font("Arial", Font.BOLD, 15));
		lblPeriod.setBounds(371, 5, 58, 15);
		frame.getContentPane( ).add(lblPeriod);

		JButton btnModify = new JButton("تعديل");
		btnModify.setFont(new Font("Arial", Font.BOLD, 15));
		btnModify.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				int dialogResult = JOptionPane.showConfirmDialog(null, "Sure?");
				if (dialogResult != JOptionPane.YES_OPTION) return;

				MedicalCertif oldc = getSelectedMedical(empl, table);
				MedicalCertif newc = new MedicalCertif(oldc.getId( ),
					DateUtil.parseDate(tf_from.getText( )),
					Integer.parseInt(tf_ndays.getText( )), tf_s.getText( ));
				newc.setEmployeeReference(empl.getEmployeeReference( ));
				newc.update(newc);
				table.setModel(
					MedicalCertif.getMedicalModel(
						Employee.initEmployee(
							new Employee( ), empl.getEmployeeReference( ))));
				tf_from.setText("");
				tf_ndays.setText("");
			}

		});
		btnModify.setBounds(389, 365, 70, 25);
		frame.getContentPane( ).add(btnModify);

		JButton btnDelete = new JButton("حذف");
		btnDelete.setFont(new Font("Arial", Font.BOLD, 15));
		btnDelete.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				int dialogResult = JOptionPane.showConfirmDialog(null, "Sure?");
				if (dialogResult != JOptionPane.YES_OPTION) return;
				MedicalCertif oldc = getSelectedMedical(empl, table);
				oldc.setEmployeeReference(empl.getEmployeeReference( ));
				oldc.remove( );
				table.setModel(
					MedicalCertif.getMedicalModel(
						Employee.initEmployee(
							new Employee( ), empl.getEmployeeReference( ))));
				tf_from.setText("");
				tf_ndays.setText("");
			}
		});
		btnDelete.setBounds(303, 365, 70, 25);
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

					String query = "select id from medical where refe = '"
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

					MedicalCertif m = new MedicalCertif(lastid + 1,
						DateUtil.parseDate(tf_from.getText( )),
						Integer.parseInt(tf_ndays.getText( )), tf_s.getText( ));
					m.setEmployeeReference(empl.getEmployeeReference( ));
					m.add( );
					table.setModel(
						MedicalCertif.getMedicalModel(
							Employee.initEmployee(
								new Employee( ),
								empl.getEmployeeReference( ))));
					setupJTable(table);
					btnAdd.setText("جديد");
					btnModify.setEnabled(true);
					btnDelete.setEnabled(true);
				} else {
					tf_from.setText("");
					tf_ndays.setText("");
					btnAdd.setText("إضافة");
					btnModify.setEnabled(!true);
					btnDelete.setEnabled(!true);
				}
			}
		});
		btnAdd.setBounds(471, 365, 70, 25);
		frame.getContentPane( ).add(btnAdd);

		table = new JTable(MedicalCertif.getMedicalModel(empl));
		table.setFont(new Font("Arial", Font.PLAIN, 15));
		table.addMouseListener(new MouseAdapter( ) {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnAdd.setText("جديد");
				btnModify.setEnabled(true);
				btnDelete.setEnabled(true);
				MedicalCertif m = getSelectedMedical(empl, table);
				tf_from.setText(DateUtil.parseDate(m.getFrom( )));
				tf_ndays.setText("" + m.getNumberOfDays( ));
				tf_s.setText(m.getPeriod( ));
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

	private MedicalCertif getSelectedMedical(Employee empl, JTable table) {
		Date from = DateUtil.parseDate(
			table.getModel( ).getValueAt(table.getSelectedRow( ), 0)
							.toString( ));
		int ndays = Integer.parseInt(
			table.getModel( ).getValueAt(table.getSelectedRow( ), 2)
							.toString( ));
		String period = table.getModel( ).getValueAt(table.getSelectedRow( ), 3)
						.toString( );
		int theID = 1;

		String query = "select id from medical where refe = '"
						+ empl.getEmployeeReference( ) + "' and from = '"
						+ DateUtil.parseDate(from) + "' and ndays = '" + ndays
						+ "' and period = '" + period + "'";
		System.err.println(query);
		ResultSet r = new DAO( ).exec(query, false);

		try {
			while (r.next( )) {
				theID = r.getInt("id");
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage( ));
		}
		
		MedicalCertif m = new MedicalCertif(theID, from, ndays, period);
		m.setEmployeeReference(empl.getEmployeeReference( ));

		return m;
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
