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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.alee.laf.WebLookAndFeel;

import app.Mention;
import model.Diploma;
import model.Employee;
import java.awt.Font;

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
		frame.setResizable(false);

		JPanel panel = new JPanel( );
		panel.setBounds(12, 69, 499, 378);
		frame.getContentPane( ).add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane( );
		panel.add(scrollPane, BorderLayout.CENTER);

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

		JComboBox<Mention> comboMen = new JComboBox<Mention>( );
		comboMen.setFont(new Font("Arial", Font.BOLD, 15));
		comboMen.setModel(new DefaultComboBoxModel<Mention>(Mention.values( )));
		comboMen.setBounds(378, 33, 114, 24);
		frame.getContentPane( ).add(comboMen);

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
					Mention.parseMention(
						comboMen.getSelectedItem( ).toString( )));
				d.setEmployeeReference(empl.getEmployeeReference( ));
				old.update(d);
				table.setModel(
					Diploma.getDiplomasModel(
						Employee.initEmployee(
							new Employee( ), empl.getEmployeeReference( ))));
				tf_session.setText("");
				tf_ins.setText("");
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
				d.remove( );
				table.setModel(
					Diploma.getDiplomasModel(
						Employee.initEmployee(
							new Employee( ), empl.getEmployeeReference( ))));
				tf_session.setText("");
				tf_ins.setText("");
			}
		});
		btnDelete.setBounds(273, 459, 70, 25);
		frame.getContentPane( ).add(btnDelete);

		JButton btnAdd = new JButton("إضافة");
		btnAdd.setFont(new Font("Arial", Font.BOLD, 15));
		btnAdd.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand( ).compareTo("" + "إضافة") == 0) {
					int dialogResult = JOptionPane.showConfirmDialog(null, "Sure?");
					if (dialogResult != JOptionPane.YES_OPTION) return;
					Diploma d = new Diploma(
						Diploma.getLastDiplomaXmlId(empl.getElement( )) + 1,
						tf_title.getText( ), tf_ins.getText( ),
						tf_session.getText( ), Mention.parseMention(
							comboMen.getSelectedItem( ).toString( )));
					d.add( );

					table.setModel(
						Diploma.getDiplomasModel(
							Employee.initEmployee(
								new Employee( ), empl.getEmployeeReference( ))));
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
		int theID = Diploma.getDiplomaXmlId(
			empl.getElement( ), table.getSelectedRow( ));
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
