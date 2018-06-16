package wins.crud;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

import app.utils.XmlFile;
import model.Employee;
import model.Modeling;
import model.Repayment;

public class RepaymentCrud {

	private JFrame frame;

	public JFrame getFrame( ) {
		return frame;
	}

	private JTable table;
	private JTextField tf_ndays;
	private JTextField tf_repayed;
	private JTextField tf_holiday;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable( ) {
			public void run( ) {
				try {
					RepaymentCrud window = new RepaymentCrud(new Employee( ));
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
	public RepaymentCrud(Employee empl) {
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
		frame.setBounds(100, 100, 553, 426);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane( ).setLayout(null);

		JPanel panel = new JPanel( );
		panel.setBounds(13, 32, 528, 317);
		frame.getContentPane( ).add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane( );
		panel.add(scrollPane, BorderLayout.CENTER);

		tf_holiday = new JTextField( );
		tf_holiday.setColumns(10);
		tf_holiday.setBounds(427, 2, 114, 24);
		frame.getContentPane( ).add(tf_holiday);

		tf_ndays = new JTextField( );
		tf_ndays.setBounds(72, 0, 114, 24);
		frame.getContentPane( ).add(tf_ndays);
		tf_ndays.setColumns(10);

		tf_repayed = new JTextField( );
		tf_repayed.setBounds(258, 0, 114, 24);
		frame.getContentPane( ).add(tf_repayed);
		tf_repayed.setColumns(10);

		JLabel lblFrom = new JLabel("تاريخ البدئ");
		lblFrom.setBounds(187, 5, 70, 15);
		frame.getContentPane( ).add(lblFrom);

		JLabel lblNdays = new JLabel("عدد الأيام");
		lblNdays.setBounds(1, 5, 70, 15);
		frame.getContentPane( ).add(lblNdays);

		JLabel lblPeriod = new JLabel("الأسدس");
		lblPeriod.setBounds(373, 5, 58, 15);
		frame.getContentPane( ).add(lblPeriod);

		JLabel lbltotal = new JLabel("");
		lbltotal.setBounds(23, 361, 85, 15);
		frame.getContentPane( ).add(lbltotal);

		JButton btnModify = new JButton("تعديل");
		btnModify.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				int dialogResult = JOptionPane.showConfirmDialog(null, "Sure?");
				if (dialogResult != JOptionPane.YES_OPTION) return;
				Repayment old_r = getSelectedRepayment(empl, table);
				Repayment new_r = new Repayment(old_r.getId( ),
					tf_holiday.getText( ),
					Integer.parseInt(tf_ndays.getText( )),
					Integer.parseInt(tf_repayed.getText( )));
				old_r.update(new_r);
				table.setModel(
					Modeling.getRepaymentModel(
						XmlFile.initEmployee(
							new Employee( ), empl.getEmployeeReference( ))));
				tf_repayed.setText("");
				tf_ndays.setText("");
				lbltotal.setText(getTotal(table));
			}

		});
		btnModify.setBounds(389, 358, 70, 25);
		frame.getContentPane( ).add(btnModify);

		JButton btnDelete = new JButton("حذف");
		btnDelete.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				int dialogResult = JOptionPane.showConfirmDialog(null, "Sure?");
				if (dialogResult != JOptionPane.YES_OPTION) return;
				Repayment r = getSelectedRepayment(empl, table);
				r.remove( );
				table.setModel(
					Modeling.getRepaymentModel(
						XmlFile.initEmployee(
							new Employee( ), empl.getEmployeeReference( ))));
				tf_repayed.setText("");
				tf_ndays.setText("");
				lbltotal.setText(getTotal(table));
			}
		});
		btnDelete.setBounds(303, 358, 70, 25);
		frame.getContentPane( ).add(btnDelete);

		JButton btnAdd = new JButton("إضافة");
		btnAdd.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				int dialogResult = JOptionPane.showConfirmDialog(null, "Sure?");
				if (dialogResult != JOptionPane.YES_OPTION) return;
				if (e.getActionCommand( ).compareTo("" + "إضافة") == 0) {
					Repayment r = new Repayment(
						XmlFile.getLastRepaymentId(empl.getElement( )) + 1,
						tf_holiday.getText( ),
						Integer.parseInt(tf_repayed.getText( )),
						Integer.parseInt(tf_ndays.getText( )));
					r.add( );
					table.setModel(
						Modeling.getRepaymentModel(
							XmlFile.initEmployee(
								new Employee( ), empl.getEmployeeReference( ))));
					setupJTable(table);
					lbltotal.setText(getTotal(table));
					btnAdd.setText("جديد");
					btnModify.setEnabled(true);
					btnDelete.setEnabled(true);
				} else {
					tf_repayed.setText("");
					tf_ndays.setText("");
					btnAdd.setText("إضافة");
					btnModify.setEnabled(!true);
					btnDelete.setEnabled(!true);
				}
			}
		});
		btnAdd.setBounds(471, 358, 70, 25);
		frame.getContentPane( ).add(btnAdd);

		table = new JTable(Modeling.getRepaymentModel(empl));
		table.addMouseListener(new MouseAdapter( ) {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnAdd.setText("جديد");
				btnModify.setEnabled(true);
				btnDelete.setEnabled(true);
				Repayment r = getSelectedRepayment(empl, table);
				// TODO: update text fields
				tf_repayed.setText("" + r.getRepayedDays( ));
				tf_ndays.setText("" + r.getNumberOfDays( ));
				tf_holiday.setText(r.getPeriod( ));
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
		lbltotal.setText(getTotal(table));
		
		JLabel label = new JLabel("مجموع الأيام المعوضة");
		label.setBounds(120, 361, 142, 15);
		frame.getContentPane().add(label);
	}

	private String getTotal(JTable table) {
		int sum = 0;

		for (int i = 0; i < table.getModel( ).getRowCount( ); ++i) {
			sum += Integer.parseInt(
				table.getModel( ).getValueAt(i, 2).toString( ));
		}

		return "" + sum;
	}

	private Repayment getSelectedRepayment(Employee empl, JTable table) {
		String period = table.getModel( ).getValueAt(table.getSelectedRow( ), 0)
						.toString( );
		int ndays = Integer.parseInt(
			table.getModel( ).getValueAt(table.getSelectedRow( ), 1)
							.toString( ));
		int repayed = Integer.parseInt(
			table.getModel( ).getValueAt(table.getSelectedRow( ), 2)
							.toString( ));
		int theID = XmlFile.getRepaymentId(
			empl.getElement( ), table.getSelectedRow( ));
		Repayment r = new Repayment(theID, period, ndays, repayed);
		r.setEmployeeReference(empl.getEmployeeReference( ));
		return r;
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
