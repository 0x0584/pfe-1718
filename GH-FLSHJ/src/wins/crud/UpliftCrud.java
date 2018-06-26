package wins.crud;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

import app.utils.DateUtil;
import model.Employee;
import model.Uplift;
import java.awt.Font;

public class UpliftCrud {

	private JFrame frame;

	public JFrame getFrame( ) {
		return frame;
	}

	private Uplift current;
	private JTable table;
	private JTextField tf_date;
	private JTextField tf_indice;
	private JTextField tf_rank;
	private JTextField tf_grade;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable( ) {
			public void run( ) {
				try {
					UpliftCrud window = new UpliftCrud(new Employee( ));
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
	public UpliftCrud(Employee empl) {
		this.current = null;
		initialize(empl);
	}

	/**
	 * Create the application.
	 * 
	 * @param employee
	 */
	public UpliftCrud(Uplift current) {
		this.current = current;
		Employee empl = new Employee(current.getEmployeeReference( ));
		initialize(empl);
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @param empl
	 */
	private void initialize(Employee empl) {
		WebLookAndFeel.install( );
		String rank, grade, date, indice;

		rank = this.current != null ? "" + current.getRank( ) : "";
		grade = this.current != null ? "" + current.getGrade( ) : "";
		date = this.current != null
						? "" + DateUtil.parseDate(current.getDate( ))
						: "";
		indice = this.current != null ? "" + current.getIndice( ) : "";

		frame = new JFrame( );
		frame.setBounds(100, 100, 662, 542);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane( ).setLayout(null);
		frame.setResizable(false);

		JPanel panel = new JPanel( );
		panel.setBounds(12, 66, 638, 396);
		frame.getContentPane( ).add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane( );
		panel.add(scrollPane, BorderLayout.CENTER);

		tf_rank = new JTextField(rank);
		tf_rank.setFont(new Font("Arial", Font.PLAIN, 15));
		tf_rank.setColumns(10);
		tf_rank.setBounds(462, 30, 147, 24);
		frame.getContentPane( ).add(tf_rank);

		tf_date = new JTextField(date);
		tf_date.setFont(new Font("Arial", Font.PLAIN, 15));
		tf_date.setBounds(124, 0, 162, 24);
		frame.getContentPane( ).add(tf_date);
		tf_date.setColumns(10);

		tf_indice = new JTextField(indice);
		tf_indice.setFont(new Font("Arial", Font.PLAIN, 15));
		tf_indice.setBounds(462, 0, 147, 24);
		frame.getContentPane( ).add(tf_indice);
		tf_indice.setColumns(10);

		tf_grade = new JTextField(grade);
		tf_grade.setFont(new Font("Arial", Font.PLAIN, 15));
		tf_grade.setColumns(10);
		tf_grade.setBounds(124, 30, 162, 24);
		frame.getContentPane( ).add(tf_grade);

		JLabel label = new JLabel("السلم");
		label.setFont(new Font("Arial", Font.BOLD, 15));
		label.setBounds(63, 35, 58, 15);
		frame.getContentPane( ).add(label);

		JLabel lblFrom = new JLabel("الرقم الإستدلالي");
		lblFrom.setFont(new Font("Arial", Font.BOLD, 15));
		lblFrom.setBounds(337, 5, 107, 15);
		frame.getContentPane( ).add(lblFrom);

		JLabel lbldate = new JLabel("التاريخ");
		lbldate.setFont(new Font("Arial", Font.BOLD, 15));
		lbldate.setBounds(57, 5, 70, 15);
		frame.getContentPane( ).add(lbldate);

		JLabel lblPeriod = new JLabel("الرتبة");
		lblPeriod.setFont(new Font("Arial", Font.BOLD, 15));
		lblPeriod.setBounds(361, 35, 58, 15);
		frame.getContentPane( ).add(lblPeriod);

		JButton btnModify = new JButton("تعديل");
		btnModify.setFont(new Font("Arial", Font.BOLD, 15));
		btnModify.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				int dialogResult = JOptionPane.showConfirmDialog(null, "Sure?");
				if (dialogResult != JOptionPane.YES_OPTION) return;
				Uplift old_r = getSelectedUplift(empl, table);
				Uplift new_r = new Uplift(old_r.getId( ), tf_indice.getText( ),
					DateUtil.parseDate(tf_date.getText( )),
					Short.parseShort(tf_grade.getText( )),
					Short.parseShort(tf_rank.getText( )));
				old_r.setEmployeeReference(empl.getEmployeeReference( ));
				old_r.update(new_r);
				table.setModel(
					Uplift.getUpliftModel(
						Employee.initEmployee(
							new Employee( ), empl.getEmployeeReference( ))));
				tf_indice.setText("");
				tf_date.setText("");
				tf_rank.setText("");
				tf_grade.setText("");
			}

		});
		btnModify.setBounds(498, 475, 70, 25);
		frame.getContentPane( ).add(btnModify);

		JButton btnDelete = new JButton("حذف");
		btnDelete.setFont(new Font("Arial", Font.BOLD, 15));
		btnDelete.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				int dialogResult = JOptionPane.showConfirmDialog(null, "Sure?");
				if (dialogResult != JOptionPane.YES_OPTION) return;
				Uplift r = getSelectedUplift(empl, table);
				r.remove( );
				table.setModel(
					Uplift.getUpliftModel(
						Employee.initEmployee(
							new Employee( ), empl.getEmployeeReference( ))));
				tf_indice.setText("");
				tf_date.setText("");
				tf_rank.setText("");
				tf_grade.setText("");
			}
		});
		btnDelete.setBounds(412, 475, 70, 25);
		frame.getContentPane( ).add(btnDelete);

		JButton btnAdd = new JButton("إضافة");
		btnAdd.setFont(new Font("Arial", Font.BOLD, 15));
		btnAdd.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand( ).compareTo("" + "إضافة") == 0) {
					int dialogResult = JOptionPane.showConfirmDialog(null, "Sure?");
					if (dialogResult != JOptionPane.YES_OPTION) return;
					Uplift new_r = new Uplift(
						Uplift.getLastUpliftXmlId(empl.getElement( )) + 1,
						tf_indice.getText( ),
						DateUtil.parseDate(tf_date.getText( )),
						Short.parseShort(tf_grade.getText( )),
						Short.parseShort(tf_rank.getText( )));
					new_r.setEmployeeReference(empl.getEmployeeReference( ));
					new_r.add( );
					table.setModel(
						Uplift.getUpliftModel(
							Employee.initEmployee(
								new Employee( ), empl.getEmployeeReference( ))));
					setupJTable(table);
					btnAdd.setText("جديد");
					btnModify.setEnabled(true);
					btnDelete.setEnabled(true);
				} else {
					tf_indice.setText("");
					tf_date.setText("");
					tf_rank.setText("");
					tf_grade.setText("");
					btnAdd.setText("إضافة");
					btnModify.setEnabled(!true);
					btnDelete.setEnabled(!true);
				}
			}
		});
		btnAdd.setBounds(580, 475, 70, 25);
		frame.getContentPane( ).add(btnAdd);

		table = new JTable(Uplift.getUpliftModel(empl));
		table.setFont(new Font("Arial", Font.PLAIN, 15));
		table.addMouseListener(new MouseAdapter( ) {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnAdd.setText("جديد");
				btnModify.setEnabled(true);
				btnDelete.setEnabled(true);
				Uplift u = getSelectedUplift(empl, table);
				tf_indice.setText("" + u.getIndice( ));
				tf_date.setText(DateUtil.parseDate(u.getDate( )));
				tf_rank.setText("" + u.getRank( ));
				tf_grade.setText("" + u.getGrade( ));
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

	private Uplift getSelectedUplift(Employee empl, JTable table) {
		String indice = table.getModel( ).getValueAt(table.getSelectedRow( ), 1)
						.toString( );
		Date date = DateUtil.parseDate(
			table.getModel( ).getValueAt(table.getSelectedRow( ), 0)
							.toString( ));
		short grade = Short.parseShort(
			table.getModel( ).getValueAt(table.getSelectedRow( ), 2)
							.toString( ));
		short rank = Short.parseShort(
			table.getModel( ).getValueAt(table.getSelectedRow( ), 3)
							.toString( ));

		int theID = Uplift.getUpliftXmlId(
			empl.getElement( ), table.getSelectedRow( ));
		Uplift u = new Uplift(theID, indice, date, grade, rank);
		u.setEmployeeReference(empl.getEmployeeReference( ));
		return u;
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
