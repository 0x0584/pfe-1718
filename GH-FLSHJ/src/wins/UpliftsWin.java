package wins;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.alee.laf.WebLookAndFeel;

import app.Period;
import model.Employee;
import model.Uplift;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpliftsWin {

	private JFrame frame;
	private JTable table_1;

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
					UpliftsWin window = new UpliftsWin(new Employee( ));
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
	public UpliftsWin(Employee empl) {
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
		panel.setBounds(12, 12, 638, 396);
		frame.getContentPane( ).add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane( );
		panel.add(scrollPane, BorderLayout.CENTER);

		table_1 = new JTable(Uplift.trackUplifts(Period.ONE_SEMESTRE));
		scrollPane.setViewportView(table_1);

		JButton button = new JButton("السجل الكامل");
		button.setBounds(511, 420, 129, 25);
		frame.getContentPane( ).add(button);

		JComboBox<Period> cmbx = new JComboBox<Period>( );
		cmbx.setModel(new DefaultComboBoxModel<Period>(Period.values( )));
		cmbx.setBounds(398, 420, 101, 24);
		frame.getContentPane( ).add(cmbx);

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				table_1.setModel(
					Uplift.trackUplifts(
						Period.parse(cmbx.getSelectedItem( ).toString( ))));
			}
		});
		btnOk.setBounds(332, 420, 54, 25);
		frame.getContentPane( ).add(btnOk);

	}
}
