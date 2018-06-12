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
import app.utils.XmlFile;
import model.Employee;
import model.Uplift;

public class UpliftsWin {

	private JFrame frame;
	private JTable table_1;
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

		table_1 = new JTable(Uplift.getUpcoming(Period.ONE_SEMESTRE));
		scrollPane.setViewportView(table_1);

		JButton button = new JButton("السجل الكامل");
		button.setBounds(509, 12, 129, 25);
		frame.getContentPane( ).add(button);

		JComboBox<Period> cmbx = new JComboBox<Period>( );
		cmbx.addItemListener(new ItemListener( ) {
			public void itemStateChanged(ItemEvent e) {
				table_1.setModel(
					Uplift.getUpcoming(
						Period.parse(cmbx.getSelectedItem( ).toString( ))));
			}
		});
		cmbx.setModel(new DefaultComboBoxModel<Period>(Period.values( )));
		cmbx.setBounds(301, 12, 101, 24);
		frame.getContentPane( ).add(cmbx);

		JPanel panel_1 = new JPanel( );
		panel_1.setBounds(12, 268, 638, 197);
		frame.getContentPane( ).add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_1 = new JScrollPane( );
		panel_1.add(scrollPane_1, BorderLayout.CENTER);

		tbl_pending = new JTable(Uplift.getPending( ));
		scrollPane_1.setViewportView(tbl_pending);

		JButton btnConfirm = new JButton("مصادقة");
		btnConfirm.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				Uplift u = getSelectedUplift(tbl_pending);
				new UpliftWin(u).getFrame( ).setVisible(true);
			}
		});
		btnConfirm.setBounds(553, 477, 86, 25);
		frame.getContentPane( ).add(btnConfirm);

		JLabel lblUpcoming = new JLabel("الترقيات المقبلة");
		lblUpcoming.setBounds(127, 17, 108, 15);
		frame.getContentPane( ).add(lblUpcoming);

		JLabel lblWaitingForComfirmation = new JLabel("ترقيات تنتضر المصادقة");
		lblWaitingForComfirmation.setBounds(127, 247, 147, 15);
		frame.getContentPane( ).add(lblWaitingForComfirmation);

		JLabel label = new JLabel("تحديد الأجل");
		label.setBounds(421, 17, 70, 15);
		frame.getContentPane( ).add(label);

		JButton button_1 = new JButton("*");
		button_1.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				table_1.setModel(
					Uplift.getUpcoming(
						Period.parse(cmbx.getSelectedItem( ).toString( ))));
				tbl_pending.setModel(Uplift.getPending( ));

			}
		});
		button_1.setBounds(12, 472, 47, 25);
		frame.getContentPane( ).add(button_1);

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

		int theID = XmlFile.getLastUpliftId(empl.getElement( )) + 1;
		Uplift u = new Uplift(theID, indice, date, grade, rank);
		u.setEmployeeRefrence(empl.getReference( ));

		return u;
	}
}
