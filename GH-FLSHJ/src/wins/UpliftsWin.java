package wins;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.alee.laf.WebLookAndFeel;

import app.Period;
import model.Employee;
import model.Uplift;
import views.UpcomingUpliftsView;
import wins.crud.InfoCrud;
import wins.crud.UpliftCrud;
import app.InNext;

public class UpliftsWin {

	private JFrame frame;
	private JTable tbl_upcoming;
	private JTable tbl_pending;

	public JFrame getFrame( ) {
		return frame;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable( ) {
			public void run( ) {
				try {
					UpliftsWin window = new UpliftsWin( );
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
	public UpliftsWin() {
		initialize( );
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @param empl
	 */
	private void initialize( ) {
		WebLookAndFeel.install( );

		frame = new JFrame( );
		frame.setBounds(100, 100, 662, 539);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane( ).setLayout(null);

		JPanel panel = new JPanel( );
		panel.setBounds(12, 38, 638, 197);
		frame.getContentPane( ).add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane( );
		panel.add(scrollPane, BorderLayout.CENTER);

		tbl_upcoming = new JTable(Uplift.getUpcomingUplifts(Period.TODAY));
		scrollPane.setViewportView(tbl_upcoming);

		JButton button = new JButton("السجل الكامل");
		button.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				try {
					new InfoCrud(new Employee(tbl_upcoming.getModel( )
									.getValueAt(tbl_upcoming.getSelectedRow( ), 0)
									.toString( ))).getFrame( ).setVisible(true);
				} catch (Exception x) {
					System.err.println(x.getMessage( ));
				}
			}
		});
		button.setBounds(517, 12, 129, 25);
		frame.getContentPane( ).add(button);

		JComboBox<Period> cmbx = new JComboBox<Period>( );
		cmbx.addItemListener(new ItemListener( ) {
			public void itemStateChanged(ItemEvent e) {
				tbl_upcoming.setModel(
					Uplift.getUpcomingUplifts(
						Period.parse(cmbx.getSelectedItem( ).toString( ))));
			}
		});
		cmbx.setModel(new DefaultComboBoxModel<Period>(Period.values( )));
		cmbx.setBounds(322, 12, 101, 24);
		frame.getContentPane( ).add(cmbx);

		JPanel panel_1 = new JPanel( );
		panel_1.setBounds(12, 268, 638, 197);
		frame.getContentPane( ).add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_1 = new JScrollPane( );
		panel_1.add(scrollPane_1, BorderLayout.CENTER);

		tbl_pending = new JTable(Uplift.getPendingUplifts( ));
		scrollPane_1.setViewportView(tbl_pending);

		JButton btnConfirm = new JButton("مصادقة");
		btnConfirm.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				Uplift u = getSelectedUplift(tbl_pending);
				new UpliftCrud(u).getFrame( ).setVisible(true);
			}
		});
		btnConfirm.setBounds(553, 477, 86, 25);
		frame.getContentPane( ).add(btnConfirm);

		JLabel lblUpcoming = new JLabel("الترقيات المقبلة");
		lblUpcoming.setBounds(202, 17, 108, 15);
		frame.getContentPane( ).add(lblUpcoming);

		JLabel lblWaitingForComfirmation = new JLabel("ترقيات تنتضر المصادقة");
		lblWaitingForComfirmation.setBounds(127, 247, 147, 15);
		frame.getContentPane( ).add(lblWaitingForComfirmation);

		JLabel label = new JLabel("تحديد الأجل");
		label.setBounds(435, 17, 70, 15);
		frame.getContentPane( ).add(label);

		JButton button_1 = new JButton("*");
		button_1.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				tbl_upcoming.setModel(
					Uplift.getUpcomingUplifts(
						Period.parse(cmbx.getSelectedItem( ).toString( ))));
				tbl_pending.setModel(Uplift.getPendingUplifts( ));

			}
		});
		button_1.setBounds(12, 472, 47, 25);
		frame.getContentPane( ).add(button_1);

		JButton button_2 = new JButton("السجل الكامل");
		button_2.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				try {
					new InfoCrud(new Employee(
						getSelectedUplift(tbl_pending).getEmployeeReference( )))
										.getFrame( ).setVisible(true);
				} catch (Exception x) {
					System.err.println(x.getMessage( ));

				}
			}
		});
		button_2.setBounds(509, 242, 129, 25);
		frame.getContentPane( ).add(button_2);
		
		JComboBox<InNext> combx = new JComboBox<InNext>();
		combx.setModel(new DefaultComboBoxModel<InNext>(InNext.values()));
		combx.setBounds(110, 12, 80, 24);
		frame.getContentPane().add(combx);
		
		JButton button_3 = new JButton("إستخراج");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = Integer.parseInt(combx.getSelectedItem( ).toString( ));
				i -= 1900; // date shit!
				new UpcomingUpliftsView(Uplift.getInNextModel(i)).getFrame( ).setVisible(true);
			}
		});
		button_3.setBounds(12, 12, 86, 25);
		frame.getContentPane().add(button_3);


	}

	protected Uplift getSelectedUplift(JTable table) {
		Employee empl = new Employee(table.getModel( )
						.getValueAt(table.getSelectedRow( ), 0).toString( ));
		String indice = "";
		Date date = new Date( );
		short grade = Short.parseShort(
			table.getModel( ).getValueAt(table.getSelectedRow( ), 3)
							.toString( ));
		short rank = Short.parseShort(
			table.getModel( ).getValueAt(table.getSelectedRow( ), 4)
							.toString( ));

		int theID = Uplift.getLastUpliftXmlId(empl.getElement( )) + 1;
		Uplift u = new Uplift(theID, indice, date, grade, rank);
		u.setEmployeeReference(empl.getEmployeeReference( ));

		return u;
	}
}
