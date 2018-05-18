package view;

import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import model.Employee;
import operation.Printer;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.awt.Color;

public class AttTravailView {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable( ) {
			public void run( ) {
				try {
					AttTravailView window = new AttTravailView(new Employee( ),
						true);
					window.frame.setVisible(true);
					PrinterJob pjob = PrinterJob.getPrinterJob( );
					PageFormat preformat = pjob.defaultPage( );
					preformat.setOrientation(PageFormat.LANDSCAPE);
					PageFormat postformat = pjob.pageDialog(preformat);

					// if user does not hit cancel then print.
					if (preformat != postformat) {
						// Set print component
						pjob.setPrintable(
							new Printer(window.frame), postformat);
						// have to find
						if (pjob.printDialog( )) {
							try {
								pjob.print( );
							} catch (PrinterException e1) {
								e1.printStackTrace( );
								System.err.println(e1.getMessage( ));
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace( );
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * TODO: add employee and professor
	 */
	public AttTravailView(Employee e, boolean isprof) {
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
		frame.setBounds(100, 100, 565, 638);
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
		lblRoyaumeDuMaroc.setBounds(22, 70, 154, 15);
		frame.getContentPane( ).add(lblRoyaumeDuMaroc);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(22, 39, 70, 15);
		frame.getContentPane( ).add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(22, 12, 70, 15);
		frame.getContentPane( ).add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(22, 97, 70, 15);
		frame.getContentPane( ).add(lblNewLabel_2);

		JLabel label = new JLabel("Royaume du Maroc");
		label.setBounds(409, 12, 134, 15);
		frame.getContentPane( ).add(label);

		JLabel label_1 = new JLabel("New label");
		label_1.setBounds(473, 39, 70, 15);
		frame.getContentPane( ).add(label_1);

		JLabel label_2 = new JLabel("New label");
		label_2.setBounds(473, 70, 70, 15);
		frame.getContentPane( ).add(label_2);

		JLabel label_3 = new JLabel("New label");
		label_3.setBounds(473, 97, 70, 15);
		frame.getContentPane( ).add(label_3);

		JPanel panel = new JPanel( );
		panel.setBackground(Color.WHITE);
		panel.setBounds(235, 12, 95, 95);
		frame.getContentPane( ).add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		try {
			BufferedImage myPicture = null;
			myPicture = ImageIO.read(new File("data/imgs/logo.png"));
			JLabel label_11 = new JLabel(new ImageIcon(myPicture));
			panel.add(label_11, BorderLayout.CENTER);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace( );
		}

		JLabel label_4 = new JLabel(
			"______________________________________________________");
		label_4.setFont(new Font("Dialog", Font.BOLD, 17));
		label_4.setBounds(32, 97, 500, 33);
		frame.getContentPane( ).add(label_4);

		JLabel lblLeDoyenDe = new JLabel(
			"Le Doyen de la faculte des Lettres et des Sciences Humaines");
		lblLeDoyenDe.setBounds(56, 184, 442, 15);
		frame.getContentPane( ).add(lblLeDoyenDe);

		JLabel lblAttesteQueMr = new JLabel("atteste que Mr:");
		lblAttesteQueMr.setBounds(42, 204, 134, 15);
		frame.getContentPane( ).add(lblAttesteQueMr);

		JLabel lblNomCompletes = new JLabel(String.format(
			"Nom complete: %s %s", e.getFamilyname( ), e.getName( )));
		lblNomCompletes.setBounds(67, 251, 431, 15);
		frame.getContentPane( ).add(lblNomCompletes);

		JLabel label_5 = new JLabel(String.format(
			"Nationalite: %s", e.isMoroccan( ) ? "Maroccaine" : "Etrange"));
		label_5.setBounds(67, 278, 431, 15);
		frame.getContentPane( ).add(label_5);

		JLabel label_6 = new JLabel(String.format(
			"Grade: %s",
			e.getUplifts( ).get(e.getUplifts( ).size( ) - 1).getGrade( )));
		label_6.setBounds(67, 305, 159, 15);
		frame.getContentPane( ).add(label_6);

		JLabel label_7 = new JLabel(
			String.format("S.O.M.: %s", e.getReference( )));
		label_7.setBounds(67, 332, 431, 15);
		frame.getContentPane( ).add(label_7);

		JLabel lblExerceSeFonctions = new JLabel(
			"Exerce   se   fonctions  dans   cet  etablissement   depuis");
		lblExerceSeFonctions.setBounds(56, 383, 432, 15);
		frame.getContentPane( ).add(lblExerceSeFonctions);

		JLabel lblElJadidaLe = new JLabel(
			String.format("El Jadida le: %s", new Date( )));
		lblElJadidaLe.setBounds(137, 567, 291, 15);
		frame.getContentPane( ).add(lblElJadidaLe);

		// TODO: verify whether it's hiring date or joining date
		JLabel label_10 = new JLabel(
			String.format(" le %s", e.getHiringdate( ).toString( )));
		label_10.setBounds(42, 403, 354, 15);
		frame.getContentPane( ).add(label_10);

		JLabel label_8 = new JLabel(
			"La presente attestation est delivree a l'interesse(e) pour servir ");
		label_8.setBounds(56, 438, 499, 15);
		frame.getContentPane( ).add(label_8);

		JLabel label_9 = new JLabel("et valoir ce que de droit");
		label_9.setBounds(42, 457, 184, 15);
		frame.getContentPane( ).add(label_9);
	}
}
