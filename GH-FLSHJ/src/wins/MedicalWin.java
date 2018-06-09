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
import model.MedicalCertif;
import model.Modeling;

public class MedicalWin {

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
					MedicalWin window = new MedicalWin(new Employee( ));
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
	public MedicalWin(Employee empl) {
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
		panel.setBounds(12, 69, 528, 317);
		frame.getContentPane( ).add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane( );
		panel.add(scrollPane, BorderLayout.CENTER);

		tf_s = new JTextField( );
		tf_s.setColumns(10);
		tf_s.setBounds(427, 2, 114, 24);
		frame.getContentPane( ).add(tf_s);

		tf_ndays = new JTextField( );
		tf_ndays.setBounds(72, 0, 114, 24);
		frame.getContentPane( ).add(tf_ndays);
		tf_ndays.setColumns(10);

		tf_from = new JTextField( );
		tf_from.setBounds(258, 0, 114, 24);
		frame.getContentPane( ).add(tf_from);
		tf_from.setColumns(10);

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
		lbltotal.setBounds(38, 42, 114, 15);
		frame.getContentPane( ).add(lbltotal);

		JButton btnModify = new JButton("تعديل");
		btnModify.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				MedicalCertif oldc = getSelectedMedical(empl, table);
				MedicalCertif newc = new MedicalCertif(oldc.getId( ),
					DateUtils.parseDate(tf_from.getText( )),
					Integer.parseInt(tf_ndays.getText( )), tf_s.getText( ));
				XmlFile.updateMedicalCertif(empl, oldc, newc);
				table.setModel(
					Modeling.getMedicalModel(
						XmlFile.initEmployee(
							new Employee( ), empl.getReference( ))));
				tf_from.setText("");
				tf_ndays.setText("");
				lbltotal.setText(getTotal(table));
			}

		});
		btnModify.setBounds(388, 32, 70, 25);
		frame.getContentPane( ).add(btnModify);

		JButton btnDelete = new JButton("حذف");
		btnDelete.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				MedicalCertif oldc = getSelectedMedical(empl, table);
				XmlFile.deleteMedicalCertif(empl, oldc);
				table.setModel(
					Modeling.getMedicalModel(
						XmlFile.initEmployee(
							new Employee( ), empl.getReference( ))));
				tf_from.setText("");
				tf_ndays.setText("");
				lbltotal.setText(getTotal(table));
			}
		});
		btnDelete.setBounds(302, 32, 70, 25);
		frame.getContentPane( ).add(btnDelete);

		JButton btnAdd = new JButton("إضافة");
		btnAdd.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand( ).compareTo(""
								+ "إضافة") == 0) {
					XmlFile.addMedicalCertif(
						empl,
						new MedicalCertif(XmlFile.getLastMedicalId(empl)+1,
							DateUtils.parseDate(tf_from.getText( )),
							Integer.parseInt(tf_ndays.getText( )),
							tf_s.getText( )));
					table.setModel(
						Modeling.getMedicalModel(
							XmlFile.initEmployee(
								new Employee( ), empl.getReference( ))));
					setupJTable(table);
					lbltotal.setText(getTotal(table));
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
		btnAdd.setBounds(470, 32, 70, 25);
		frame.getContentPane( ).add(btnAdd);

		table = new JTable(Modeling.getMedicalModel(empl));
		table.addMouseListener(new MouseAdapter( ) {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnAdd.setText("جديد");
				btnModify.setEnabled(true);
				btnDelete.setEnabled(true);
				MedicalCertif m = getSelectedMedical(empl, table);
				tf_from.setText(DateUtils.parseDate(m.getFrom( )));
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
		lbltotal.setText(getTotal(table));
	}

	private String getTotal(JTable table) {
		int sum = 0;

		for (int i = 0; i < table.getModel( ).getRowCount( ); ++i) {
			sum += Integer.parseInt(
				table.getModel( ).getValueAt(i, 2).toString( ));
		}

		return "" + sum;
	}

	private MedicalCertif getSelectedMedical(Employee empl, JTable table) {
		Date from = DateUtils.parseDate(
			table.getModel( ).getValueAt(table.getSelectedRow( ), 0)
							.toString( ));
		int ndays = Integer.parseInt(
			table.getModel( ).getValueAt(table.getSelectedRow( ), 2)
							.toString( ));
		String period = table.getModel( ).getValueAt(table.getSelectedRow( ), 3)
						.toString( );
		// TODO: getting id has a bug
		int theID = XmlFile.getMedicalCertifId(empl, table.getSelectedRow( ));
		return new MedicalCertif(theID, from, ndays, period);
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