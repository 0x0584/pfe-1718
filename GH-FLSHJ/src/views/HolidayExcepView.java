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
import app.utils.DateUtil;
import app.utils.Printer;
import model.Employee;

public class HolidayExcepView {

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
					HolidayExcepView window = new HolidayExcepView(
						new Employee( ), "Des raisons personnelle", new Date( ),
						DateUtil.add(Period.ONE_WEEK, new Date( ), 1));
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace( );
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HolidayExcepView(Employee e, String raison, Date from, Date to) {
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
		frame.setBounds(100, 100, Printer.A4_STD_WIDTH, Printer.A4_STD_HEIGHT);
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
			"Nom complete: %s %s", e.getFamilyName( ), e.getName( )));
		lblNomCompletes.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNomCompletes.setBounds(110, 482, 431, 29);
		frame.getContentPane( ).add(lblNomCompletes);

		JLabel label_5 = new JLabel(String.format(
			"Nationalite: %s", e.isMoroccan( ) ? "Maroccaine" : "Etrange"));
		label_5.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_5.setBounds(110, 505, 431, 33);
		frame.getContentPane( ).add(label_5);

		JLabel label_6 = new JLabel(String.format(
			"Grade: %s",
			e.getUplifts( ).get(e.getUplifts( ).size( ) - 1).getGrade( )));
		label_6.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_6.setBounds(110, 532, 159, 33);
		frame.getContentPane( ).add(label_6);

		JLabel label_7 = new JLabel(
			String.format("S.O.M.: %s", e.getEmployeeReference( )));
		label_7.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_7.setBounds(110, 559, 431, 33);
		frame.getContentPane( ).add(label_7);

		JLabel lblElJadidaLe = new JLabel(String.format(
			"El Jadida le: %s", DateUtil.parseDate(new Date( ))));
		lblElJadidaLe.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblElJadidaLe.setBounds(270, 816, 193, 48);
		frame.getContentPane( ).add(lblElJadidaLe);

		JLabel lblAttestationDeTravail = new JLabel("Conge Exceptionnel");
		lblAttestationDeTravail.setFont(
			new Font("Dialog", Font.BOLD | Font.ITALIC, 19));
		lblAttestationDeTravail.setBounds(245, 161, 243, 36);
		frame.getContentPane( ).add(lblAttestationDeTravail);

		JLabel lblAuDepartements = new JLabel(!e.isProfessor( ) ? " "
						: String.format(" %s ", e.getDepartment( )));
		lblAuDepartements.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblAuDepartements.setBounds(503, 367, 486, 29);
		frame.getContentPane( ).add(lblAuDepartements);

		JLabel lblVueLrDahir = new JLabel(
			"Vue le Dahir N 1.58.008 du 04 Chaabane 1377 (24/02/1958)");
		lblVueLrDahir.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblVueLrDahir.setBounds(110, 280, 485, 36);
		frame.getContentPane( ).add(lblVueLrDahir);

		JLabel lblPortantStautGeneral = new JLabel(
			"portant staut general de la fontion publique tel qu'ill a ete modifie ");
		lblPortantStautGeneral.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblPortantStautGeneral.setBounds(70, 295, 525, 36);
		frame.getContentPane( ).add(lblPortantStautGeneral);

		JLabel lblEtComplete = new JLabel("et complete");
		lblEtComplete.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblEtComplete.setBounds(70, 311, 525, 36);
		frame.getContentPane( ).add(lblEtComplete);

		JLabel lblDecide = new JLabel("Arrete");
		lblDecide.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 19));
		lblDecide.setBounds(322, 404, 88, 15);
		frame.getContentPane( ).add(lblDecide);

		JLabel lblArticleUnique = new JLabel("Article 1");
		lblArticleUnique.setFont(
			new Font("Dialog", Font.BOLD | Font.ITALIC, 19));
		lblArticleUnique.setBounds(70, 435, 158, 15);
		frame.getContentPane( ).add(lblArticleUnique);

		JLabel label_8 = new JLabel("- Vu la demande de l'interesse(e) ");
		label_8.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_8.setBounds(70, 343, 525, 36);
		frame.getContentPane( ).add(label_8);

		JLabel label_9 = new JLabel(
			"- Vu l'avis favorable du chef du departement concerne: ");
		label_9.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_9.setBounds(70, 363, 525, 36);
		frame.getContentPane( ).add(label_9);

		JLabel label_10 = new JLabel(
			"L'interesse(e) est autorise(e) a quitter le territoire national durant");
		label_10.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_10.setBounds(110, 653, 513, 39);
		frame.getContentPane( ).add(label_10);

		JLabel label_11 = new JLabel(
			String.format(" la period susvise pour: %s", raison));
		label_11.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_11.setBounds(70, 676, 566, 29);
		frame.getContentPane( ).add(label_11);

		JLabel label_12 = new JLabel(
			"L'interesse(e) doit, a l'expiration de ce conge aviser le service competent");
		label_12.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_12.setBounds(70, 704, 566, 33);
		frame.getContentPane( ).add(label_12);

		JLabel label_13 = new JLabel("de la reprise de ses fonction");
		label_13.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_13.setBounds(70, 719, 356, 33);
		frame.getContentPane( ).add(label_13);

		JLabel label_14 = new JLabel("Article 2");
		label_14.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 19));
		label_14.setBounds(70, 638, 158, 15);
		frame.getContentPane( ).add(label_14);

		JLabel label_15 = new JLabel(String.format(
			"un conge exceptionnel du %s a %s", DateUtil.parseDate(from),
			DateUtil.parseDate(to)));
		label_15.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_15.setBounds(176, 426, 566, 33);
		frame.getContentPane( ).add(label_15);

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
