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
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.alee.laf.WebLookAndFeel;

import app.Period;
import app.utils.DateUtils;
import app.utils.Printer;
import model.Employee;

public class HolidayAdminiView {

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
					HolidayAdminiView window = new HolidayAdminiView(
						new Employee( ), null, new Date( ),
						DateUtils.add(Period.ONE_MONTH, new Date( ), 1));
					window.frame.setVisible(true);
				} catch (Exception e) {
					System.err.println(e.getMessage( ));
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HolidayAdminiView(Employee e, String raison, Date from, Date to) {
		initialize(e, e.isProfessor( ), raison, from, to);
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @param isprof
	 * @param e
	 */
	private void initialize(Employee e, boolean isprof, String raison,
		Date from, Date to) {
		WebLookAndFeel.install( );

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

		JLabel lblAttesteQueMr = new JLabel("d'El Jadida");
		lblAttesteQueMr.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblAttesteQueMr.setBounds(70, 249, 525, 36);
		frame.getContentPane( ).add(lblAttesteQueMr);

		JLabel lblNomCompletes = new JLabel(String.format(
			"Nom complete: %s %s", e.getFamilyname( ), e.getName( )));
		lblNomCompletes.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNomCompletes.setBounds(110, 557, 431, 29);
		frame.getContentPane( ).add(lblNomCompletes);

		JLabel label_5 = new JLabel(String.format(
			"Nationalite: %s", e.isMoroccan( ) ? "Maroccaine" : "Etrange"));
		label_5.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_5.setBounds(110, 580, 431, 33);
		frame.getContentPane( ).add(label_5);

		JLabel label_6 = new JLabel(String.format(
			"Grade: %s",
			e.getUplifts( ).get(e.getUplifts( ).size( ) - 1).getGrade( )));
		label_6.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_6.setBounds(110, 607, 159, 33);
		frame.getContentPane( ).add(label_6);

		JLabel label_7 = new JLabel(
			String.format("S.O.M.: %s", e.getReference( )));
		label_7.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_7.setBounds(110, 634, 431, 33);
		frame.getContentPane( ).add(label_7);

		JLabel lblExerceSeFonctions = new JLabel(
			"L'interesse(e) est autorise(e) a quitter le territoire national durant");
		lblExerceSeFonctions.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblExerceSeFonctions.setBounds(110, 708, 513, 39);
		frame.getContentPane( ).add(lblExerceSeFonctions);

		JLabel lblElJadidaLe = new JLabel(String.format(
			"El Jadida le: %s", DateUtils.parseDate(new Date( ))));
		lblElJadidaLe.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblElJadidaLe.setBounds(270, 831, 193, 48);
		frame.getContentPane( ).add(lblElJadidaLe);

		// TODO: verify whether it's hiring date or joining date
		JLabel lbls = new JLabel(" la period sus-indique");
		lbls.setFont(new Font("Dialog", Font.PLAIN, 16));
		lbls.setBounds(70, 731, 566, 29);
		frame.getContentPane( ).add(lbls);

		JLabel lblLinteresseeEstTenue = new JLabel(
			"L'interesse(e) est tenu(e) d'aviser le service competent de la reprise de");
		lblLinteresseeEstTenue.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblLinteresseeEstTenue.setBounds(70, 759, 566, 33);
		frame.getContentPane( ).add(lblLinteresseeEstTenue);

		JLabel lblSonTravailA = new JLabel(
			"son travail a l'experation de son conge");
		lblSonTravailA.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblSonTravailA.setBounds(70, 774, 356, 33);
		frame.getContentPane( ).add(lblSonTravailA);

		JLabel lblAttestationDeTravail = new JLabel(
			"Decision de Conge Administratif");
		lblAttestationDeTravail.setFont(
			new Font("Dialog", Font.BOLD | Font.ITALIC, 19));
		lblAttestationDeTravail.setBounds(188, 161, 356, 29);
		frame.getContentPane( ).add(lblAttestationDeTravail);

		JLabel lblVueLrDahir = new JLabel(
			"Vue le Dahir N 1.58.008 du 04 Chaabane 1377 (24/02/1958)");
		lblVueLrDahir.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblVueLrDahir.setBounds(110, 280, 485, 36);
		frame.getContentPane( ).add(lblVueLrDahir);

		JLabel lblPortantStautGeneral = new JLabel(
			"portant staut general de la fontion publique tel qu'ill a ete modifie ");
		lblPortantStautGeneral.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblPortantStautGeneral.setBounds(70, 314, 525, 36);
		frame.getContentPane( ).add(lblPortantStautGeneral);

		JLabel lblEtComplete = new JLabel("et complete");
		lblEtComplete.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblEtComplete.setBounds(70, 330, 525, 36);
		frame.getContentPane( ).add(lblEtComplete);

		JLabel lblDecide = new JLabel("DECIDE");
		lblDecide.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 19));
		lblDecide.setBounds(322, 410, 88, 15);
		frame.getContentPane( ).add(lblDecide);

		JLabel lblArticleUnique = new JLabel("Article 1");
		lblArticleUnique.setFont(
			new Font("Dialog", Font.BOLD | Font.ITALIC, 19));
		lblArticleUnique.setBounds(70, 478, 158, 15);
		frame.getContentPane( ).add(lblArticleUnique);

		JLabel lblVueLrDahir_1 = new JLabel(
			"Vue le Dahir N 1.11.10 du 14 Rabiaa I 1432 (18/02/2011)");
		lblVueLrDahir_1.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblVueLrDahir_1.setBounds(110, 297, 485, 36);
		frame.getContentPane( ).add(lblVueLrDahir_1);

		JLabel lblArticle = new JLabel("Article 2");
		lblArticle.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 19));
		lblArticle.setBounds(70, 693, 158, 15);
		frame.getContentPane( ).add(lblArticle);

		JLabel lblUnCongeAdministratif = new JLabel(String.format(
			"Un conge administratif a partir du %s est accorde ",
			DateUtils.parseDate(from)));
		lblUnCongeAdministratif.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblUnCongeAdministratif.setBounds(110, 494, 566, 33);
		frame.getContentPane( ).add(lblUnCongeAdministratif);

		@SuppressWarnings("deprecation")
		JLabel lblAuTitreDe = new JLabel(
			String.format(" au titre de l'annee %s", from.getYear( )));
		lblAuTitreDe.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblAuTitreDe.setBounds(70, 511, 566, 33);
		frame.getContentPane( ).add(lblAuTitreDe);

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
