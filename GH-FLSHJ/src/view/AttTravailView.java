package view;

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

import app.Printer;
import model.Employee;

public class AttTravailView {

	private JFrame frame;

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
					AttTravailView window = new AttTravailView(new Employee( ));
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
	public AttTravailView(Employee e) {
		initialize(e, e.isProfessor( ));
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @param isprof
	 * @param e
	 */
	private void initialize(Employee e, boolean isprof) {
		WebLookAndFeel.install( );
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		frame = new JFrame( );
		frame.getContentPane( ).setBackground(Color.WHITE);
		frame.setBounds(100, 100, Printer.STD_WIDTH, Printer.STD_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane( ).setLayout(null);

		JLabel lblRoyaumeDuMaroc = new JLabel("Royaume du Maroc");
		lblRoyaumeDuMaroc.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblRoyaumeDuMaroc.setBounds(20, 12, 154, 15);
		frame.getContentPane( ).add(lblRoyaumeDuMaroc);

		JLabel lblNewLabel = new JLabel("Faculté des Lettres et des Sciences");
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblNewLabel.setBounds(20, 65, 263, 15);
		frame.getContentPane( ).add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Université Chouaib Doukkali");
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(20, 39, 202, 15);
		frame.getContentPane( ).add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Humaines - El Jadida");
		lblNewLabel_2.setFont(new Font("Dialog", Font.PLAIN, 14));
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
		panel.setBackground(Color.WHITE);
		panel.setBounds(316, 12, 100, 100);
		frame.getContentPane( ).add(panel);

		try {
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
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

		JLabel lblLeDoyenDe = new JLabel(
			"Le Doyen de la faculte des Lettres et des Sciences Humaines");
		lblLeDoyenDe.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblLeDoyenDe.setBounds(110, 235, 485, 33);
		frame.getContentPane( ).add(lblLeDoyenDe);

		JLabel lblAttesteQueMr = new JLabel("atteste que Mr:");
		lblAttesteQueMr.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblAttesteQueMr.setBounds(70, 249, 134, 36);
		frame.getContentPane( ).add(lblAttesteQueMr);

		JLabel lblNomCompletes = new JLabel(String.format(
			"Nom complete: %s %s", e.getFamilyname( ), e.getName( )));
		lblNomCompletes.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNomCompletes.setBounds(120, 306, 431, 29);
		frame.getContentPane( ).add(lblNomCompletes);

		JLabel label_5 = new JLabel(String.format(
			"Nationalite: %s", e.isMoroccan( ) ? "Maroccaine" : "Etrange"));
		label_5.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_5.setBounds(120, 329, 431, 33);
		frame.getContentPane( ).add(label_5);

		JLabel label_6 = new JLabel(String.format(
			"Grade: %s",
			e.getUplifts( ).get(e.getUplifts( ).size( ) - 1).getGrade( )));
		label_6.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_6.setBounds(120, 356, 159, 33);
		frame.getContentPane( ).add(label_6);

		JLabel label_7 = new JLabel(
			String.format("S.O.M.: %s", e.getReference( )));
		label_7.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_7.setBounds(120, 383, 431, 33);
		frame.getContentPane( ).add(label_7);

		JLabel lblExerceSeFonctions = new JLabel(
			"Exerce    ses     fonctions       dans      cet    etablissement ");
		lblExerceSeFonctions.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblExerceSeFonctions.setBounds(110, 428, 542, 39);
		frame.getContentPane( ).add(lblExerceSeFonctions);

		JLabel lblElJadidaLe = new JLabel(
			String.format("El Jadida le: %s", fmt.format(new Date( ))));
		lblElJadidaLe.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblElJadidaLe.setBounds(276, 654, 181, 48);
		frame.getContentPane( ).add(lblElJadidaLe);

		// TODO: verify whether it's hiring date or joining date
		JLabel label_10 = new JLabel(
			String.format("depuis le %s", fmt.format(e.getHiringDate( ))));
		label_10.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_10.setBounds(70, 451, 354, 29);
		frame.getContentPane( ).add(label_10);

		JLabel label_8 = new JLabel(
			"La presente attestation est delivree a l'interesse(e) pour servir ");
		label_8.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_8.setBounds(110, 505, 566, 33);
		frame.getContentPane( ).add(label_8);

		JLabel label_9 = new JLabel("et valoir ce que de droit");
		label_9.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_9.setBounds(70, 524, 243, 33);
		frame.getContentPane( ).add(label_9);

		JLabel lblAttestationDeTravail = new JLabel("Attestation De Travail");
		lblAttestationDeTravail.setFont(
			new Font("Dialog", Font.BOLD | Font.ITALIC, 19));
		lblAttestationDeTravail.setBounds(239, 161, 243, 15);
		frame.getContentPane( ).add(lblAttestationDeTravail);

		JLabel lblAuDepartements = new JLabel(!e.isProfessor( ) ? " "
						: String.format(
							"au departement %s ", e.getDepartment( )));
		lblAuDepartements.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblAuDepartements.setBounds(240, 418, 486, 29);
		frame.getContentPane( ).add(lblAuDepartements);
		
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
