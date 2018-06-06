package wins;

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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.alee.laf.WebLookAndFeel;

import app.utils.DateUtils;
import app.utils.XmlFile;
import model.Employee;
import model.Modeling;
import model.Uplift;

public class UpliftWin {

	private JFrame frame;

	public JFrame getFrame( ) {
		return frame;
	}

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
					UpliftWin window = new UpliftWin(new Employee( ));
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
	public UpliftWin(Employee empl) {
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
		frame.setBounds(100, 100, 662, 507);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane( ).setLayout(null);

		JPanel panel = new JPanel( );
		panel.setBounds(12, 69, 638, 396);
		frame.getContentPane( ).add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane( );
		panel.add(scrollPane, BorderLayout.CENTER);

		tf_rank = new JTextField( );
		tf_rank.setColumns(10);
		tf_rank.setBounds(524, 0, 114, 24);
		frame.getContentPane( ).add(tf_rank);

		tf_date = new JTextField( );
		tf_date.setBounds(72, 0, 114, 24);
		frame.getContentPane( ).add(tf_date);
		tf_date.setColumns(10);

		tf_indice = new JTextField( );
		tf_indice.setBounds(344, 0, 114, 24);
		frame.getContentPane( ).add(tf_indice);
		tf_indice.setColumns(10);

		JLabel lblFrom = new JLabel("الرقم الإستدلالي");
		lblFrom.setBounds(236, 5, 107, 15);
		frame.getContentPane( ).add(lblFrom);

		JLabel lbldate = new JLabel("التاريخ");
		lbldate.setBounds(1, 5, 70, 15);
		frame.getContentPane( ).add(lbldate);

		JLabel lblPeriod = new JLabel("الرتبة");
		lblPeriod.setBounds(470, 3, 58, 15);
		frame.getContentPane( ).add(lblPeriod);

		JButton btnModify = new JButton("تعديل");
		btnModify.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				Uplift old_r = getSelectedUplift(empl, table);
				Uplift new_r = new Uplift(old_r.getId( ), tf_indice.getText( ),
					DateUtils.parseDate(tf_date.getText( )),
					Short.parseShort(tf_grade.getText( )),
					Short.parseShort(tf_rank.getText( )));
				XmlFile.updateUplift(empl, old_r, new_r);
				table.setModel(
					Modeling.getUpliftModel(
						XmlFile.initEmployee(
							new Employee( ), empl.getReference( ))));
				tf_indice.setText("");
				tf_date.setText("");
				tf_rank.setText("");
				tf_grade.setText("");
			}

		});
		btnModify.setBounds(388, 32, 70, 25);
		frame.getContentPane( ).add(btnModify);

		JButton btnDelete = new JButton("حذف");
		btnDelete.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				Uplift r = getSelectedUplift(empl, table);
				XmlFile.deleteUplift(empl, r);
				table.setModel(
					Modeling.getUpliftModel(
						XmlFile.initEmployee(
							new Employee( ), empl.getReference( ))));
				tf_indice.setText("");
				tf_date.setText("");
				tf_rank.setText("");
				tf_grade.setText("");
			}
		});
		btnDelete.setBounds(302, 32, 70, 25);
		frame.getContentPane( ).add(btnDelete);

		JButton btnAdd = new JButton("إضافة");
		btnAdd.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand( ).compareTo("" + "إضافة") == 0) {
					Uplift new_r = new Uplift(XmlFile.getLastUpliftId(empl) + 1,
						tf_indice.getText( ),
						DateUtils.parseDate(tf_date.getText( )),
						Short.parseShort(tf_grade.getText( )),
						Short.parseShort(tf_rank.getText( )));
					XmlFile.addUplift(empl, new_r);
					table.setModel(
						Modeling.getUpliftModel(
							XmlFile.initEmployee(
								new Employee( ), empl.getReference( ))));
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
		btnAdd.setBounds(470, 32, 70, 25);
		frame.getContentPane( ).add(btnAdd);

		table = new JTable(Modeling.getUpliftModel(empl));
		table.addMouseListener(new MouseAdapter( ) {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnAdd.setText("جديد");
				btnModify.setEnabled(true);
				btnDelete.setEnabled(true);
				Uplift u = getSelectedUplift(empl, table);
				// TODO: update text fields
				tf_indice.setText("" + u.getIndice( ));
				tf_date.setText(DateUtils.parseDate(u.getDate( )));
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

		tf_grade = new JTextField( );
		tf_grade.setColumns(10);
		tf_grade.setBounds(66, 36, 114, 24);
		frame.getContentPane( ).add(tf_grade);

		JLabel label = new JLabel("السلم");
		label.setBounds(12, 39, 58, 15);
		frame.getContentPane( ).add(label);
	}

	private Uplift getSelectedUplift(Employee empl, JTable table) {
		String indice = table.getModel( ).getValueAt(table.getSelectedRow( ), 1)
						.toString( );
		Date date = DateUtils.parseDate(
			table.getModel( ).getValueAt(table.getSelectedRow( ), 0)
							.toString( ));
		short grade = Short.parseShort(
			table.getModel( ).getValueAt(table.getSelectedRow( ), 2)
							.toString( ));
		short rank = Short.parseShort(
			table.getModel( ).getValueAt(table.getSelectedRow( ), 3)
							.toString( ));
		;
		int theID = XmlFile.getUpliftId(empl, table.getSelectedRow( ));
		return new Uplift(theID, indice, date, grade, rank);
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
