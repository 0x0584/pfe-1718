package view;

import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import model.Employee;
import model.Professor;
import operation.Printer;

import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.awt.Color;
import java.awt.FlowLayout;

public class AttTravailView {

	private JFrame frame;
	private boolean isprof;

	public JFrame getFrame( ) {
		return frame;
	}

	/**
	 * Launch the application.
	 * 
	 * TODO: find how to call both the view and the printing menu
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable( ) {
			public void run( ) {
				try {
					AttTravailView window = new AttTravailView(
						new Professor( ));
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
		isprof = (e instanceof Professor);
		initialize(e, isprof);
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @param isprof
	 * @param e
	 */
	private void initialize(Employee e, boolean isprof) {
		frame = new JFrame( );
		frame.getContentPane( ).setBackground(Color.WHITE);
		frame.setBounds(100, 100, 741, 748);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane( ).setLayout(null);

		try {
			UIManager.setLookAndFeel(
				UIManager.getInstalledLookAndFeels( )[1].getClassName( ));
		} catch (Exception e1) {
			// e.printStackTrace( );
			System.err.println(e1.getMessage( ));
		}

		JLabel lblRoyaumeDuMaroc = new JLabel("Royaume du Maroc");
		lblRoyaumeDuMaroc.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblRoyaumeDuMaroc.setBounds(20, 12, 154, 15);
		frame.getContentPane( ).add(lblRoyaumeDuMaroc);

		JLabel lblNewLabel = new JLabel("Faculté des Lettres et des Sciences");
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblNewLabel.setBounds(20, 66, 263, 15);
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
		label.setFont(new Font("Dialog", Font.PLAIN, 14));
		label.setBounds(606, 12, 100, 15);
		frame.getContentPane( ).add(label);

		JLabel label_1 = new JLabel("جامعة شعيب دكالي");
		label_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_1.setBounds(592, 39, 114, 15);
		frame.getContentPane( ).add(label_1);

		JLabel label_2 = new JLabel("كلية الآداب والعلوم الإنسانية");
		label_2.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_2.setBounds(544, 70, 162, 15);
		frame.getContentPane( ).add(label_2);

		JLabel label_3 = new JLabel("الجديدة");
		label_3.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_3.setBounds(657, 97, 49, 15);
		frame.getContentPane( ).add(label_3);

		JPanel panel = new JPanel( );
		panel.setBackground(Color.WHITE);
		panel.setBounds(320, 12, 100, 100);
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
		label_4.setBounds(120, 97, 500, 33);
		frame.getContentPane( ).add(label_4);

		JLabel lblLeDoyenDe = new JLabel(
			"Le Doyen de la faculte des Lettres et des Sciences Humaines");
		lblLeDoyenDe.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblLeDoyenDe.setBounds(110, 220, 485, 15);
		frame.getContentPane( ).add(lblLeDoyenDe);

		JLabel lblAttesteQueMr = new JLabel("atteste que Mr:");
		lblAttesteQueMr.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblAttesteQueMr.setBounds(65, 239, 134, 15);
		frame.getContentPane( ).add(lblAttesteQueMr);

		JLabel lblNomCompletes = new JLabel(String.format(
			"Nom complete: %s %s", e.getFamilyname( ), e.getName( )));
		lblNomCompletes.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNomCompletes.setBounds(120, 287, 431, 15);
		frame.getContentPane( ).add(lblNomCompletes);

		JLabel label_5 = new JLabel(String.format(
			"Nationalite: %s", e.isMoroccan( ) ? "Maroccaine" : "Etrange"));
		label_5.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_5.setBounds(120, 314, 431, 15);
		frame.getContentPane( ).add(label_5);

		JLabel label_6 = new JLabel(String.format(
			"Grade: %s",
			e.getUplifts( ).get(e.getUplifts( ).size( ) - 1).getGrade( )));
		label_6.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_6.setBounds(120, 341, 159, 15);
		frame.getContentPane( ).add(label_6);

		JLabel label_7 = new JLabel(
			String.format("S.O.M.: %s", e.getReference( )));
		label_7.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_7.setBounds(120, 368, 431, 15);
		frame.getContentPane( ).add(label_7);
		Professor p = null;
		if (isprof) {
			p = (Professor) e;
		}
		JLabel lblExerceSeFonctions = new JLabel(
			"Exerce    ses     fonctions       dans      cet    etablissement ");
		lblExerceSeFonctions.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblExerceSeFonctions.setBounds(110, 419, 542, 15);
		frame.getContentPane( ).add(lblExerceSeFonctions);

		JLabel lblElJadidaLe = new JLabel(
			String.format("El Jadida le: %s", new Date( )));
		lblElJadidaLe.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblElJadidaLe.setBounds(193, 662, 354, 15);
		frame.getContentPane( ).add(lblElJadidaLe);

		// TODO: verify whether it's hiring date or joining date
		JLabel label_10 = new JLabel(
			String.format("depuis le %s", e.getHiringdate( ).toString( )));
		label_10.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_10.setBounds(isprof ? 300 : 66, isprof ? 450 : 440, 354, 15);
		frame.getContentPane( ).add(label_10);

		JLabel label_8 = new JLabel(
			"La presente attestation est delivree a l'interesse(e) pour servir ");
		label_8.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_8.setBounds(114, 523, 566, 15);
		frame.getContentPane( ).add(label_8);

		JLabel label_9 = new JLabel("et valoir ce que de droit");
		label_9.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_9.setBounds(69, 542, 243, 15);
		frame.getContentPane( ).add(label_9);

		JLabel lblAttestationDeTravail = new JLabel("Attestation De Travail");
		lblAttestationDeTravail.setFont(
			new Font("Dialog", Font.BOLD | Font.ITALIC, 19));
		lblAttestationDeTravail.setBounds(249, 145, 243, 15);
		frame.getContentPane( ).add(lblAttestationDeTravail);

		JLabel lblAuDepartements = new JLabel(
			isprof ? String.format("au departement %s ", p.getDepartment( ))
							: " ");
		lblAuDepartements.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblAuDepartements.setBounds(65, 446, 486, 23);
		frame.getContentPane( ).add(lblAuDepartements);
	}
}
