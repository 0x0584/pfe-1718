package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.alee.laf.WebLookAndFeel;

import app.utils.Printer;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JScrollPane;

public class UpcomingUpliftsView {

	private JFrame frame;
	private JTable table;

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
					UpcomingUpliftsView window = new UpcomingUpliftsView(new DefaultTableModel());
					window.frame.setVisible(true);
					Printer.doPrint(window.frame);
				} catch (Exception e) {
					e.printStackTrace( );
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UpcomingUpliftsView(TableModel table) {
		initialize(table);
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @param isprof
	 * @param e
	 */
	private void initialize(TableModel model) {
		WebLookAndFeel.install( );
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		frame = new JFrame( );
		frame.getContentPane( ).setBackground(Color.WHITE);
//		frame.setBounds(100, 100, Printer.A4_STD_WIDTH, Printer.A4_STD_HEIGHT);
		frame.setBounds(100, 100, 733, 792);

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane( ).setLayout(null);
		
		JLabel lblRoyaumeDuMaroc = new JLabel("Royaume du Maroc");
		lblRoyaumeDuMaroc.setFont(new Font("Arial", Font.PLAIN, 16));
		lblRoyaumeDuMaroc.setBounds(20, 12, 154, 15);
		frame.getContentPane( ).add(lblRoyaumeDuMaroc);

		JLabel lblNewLabel = new JLabel("Faculté des Lettres et des Sciences");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel.setBounds(20, 65, 263, 15);
		frame.getContentPane( ).add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Université Chouaib Doukkali");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(20, 39, 202, 15);
		frame.getContentPane( ).add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Humaines - El Jadida");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(20, 97, 152, 15);
		frame.getContentPane( ).add(lblNewLabel_2);

		JLabel label = new JLabel("المملكة المغربية");
		label.setFont(new Font("Arial", Font.PLAIN, 16));
		label.setBounds(626, 12, 77, 15);
		frame.getContentPane( ).add(label);

		JLabel label_1 = new JLabel("جامعة شعيب دكالي");
		label_1.setFont(new Font("Arial", Font.PLAIN, 16));
		label_1.setBounds(609, 39, 100, 15);
		frame.getContentPane( ).add(label_1);

		JLabel label_2 = new JLabel("كلية الآدب والعلوم الإنسانية");
		label_2.setFont(new Font("Arial", Font.PLAIN, 16));
		label_2.setBounds(566, 65, 143, 15);
		frame.getContentPane( ).add(label_2);

		JLabel label_3 = new JLabel("الجديدة");
		label_3.setFont(new Font("Arial", Font.PLAIN, 16));
		label_3.setBounds(662, 97, 41, 15);
		frame.getContentPane( ).add(label_3);

		JPanel panel = new JPanel( );
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel.setBackground(Color.WHITE);
		panel.setBounds(316, 12, 100, 100);
		frame.getContentPane( ).add(panel);

		try {
			JLabel label_11 = new JLabel(new ImageIcon(
				ImageIO.read(new File("data/imgs/logo.png")).getScaledInstance(
					(panel.getWidth( ) - 5), (panel.getHeight( ) - 5),
					Image.SCALE_SMOOTH)));
			label_11.setBounds(0, 0, 95, 95);
			label_11.setVisible(true);
			panel.add(label_11);
		} catch (IOException ex) {
			ex.printStackTrace( );
		}

		JLabel label_4 = new JLabel(
			"______________________________________________________");
		label_4.setFont(new Font("Dialog", Font.BOLD, 17));
		label_4.setBounds(110, 113, 500, 33);
		frame.getContentPane( ).add(label_4);

		JLabel lblElJadidaLe = new JLabel(
			String.format("El Jadida le: %s", fmt.format(new Date( ))));
		lblElJadidaLe.setFont(new Font("Arial", Font.PLAIN, 16));
		lblElJadidaLe.setBounds(528, 706, 181, 48);
		frame.getContentPane( ).add(lblElJadidaLe);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(20, 172, 682, 518);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable(model);
		scrollPane.setViewportView(table);
		
		JMenuBar menuBar = new JMenuBar( );
		frame.setJMenuBar(menuBar);

		JMenuItem mntmPrint = new JMenuItem("Print");
		mntmPrint.addMouseListener(new MouseAdapter( ) {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuBar.setVisible(false);
				Printer.doPrint(frame);
				menuBar.setVisible(true);
			}
		});
		menuBar.add(mntmPrint);
	}
}
