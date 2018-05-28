package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.alee.laf.WebLookAndFeel;

import app.utils.Printer;
import app.utils.TableCellListener;
import model.Employee;

public class NotationView {

	private final static int INDEX_NOTE_COL = 1, INDEX_TOTAL_ROW = 5;
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
					NotationView window = new NotationView(
						new Employee("0112358"));
					window.frame.setVisible(true);
					// Printer.doPrint(window.frame);
				} catch (Exception e) {
					e.printStackTrace( );
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NotationView(Employee e) {
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
		DefaultTableModel dmodel_0 = new DefaultTableModel( );

		for (String str : new String[] {
						"ملاحظات", "النقطة الممنوحة",

						"سلم التنقيط", "عناصر التنقيط",
		}) {
			dmodel_0.addColumn(str);
		}

		dmodel_0.addRow(new String[] {
						"", "", "من 0 إلى 5",

						"إنجاز المهام المرتبطة بالوظيفة"
		});
		dmodel_0.addRow(new String[] {
						"", "", "من 0 إلى 5",

						"المردودية"
		});
		dmodel_0.addRow(new String[] {
						"", "", "من 0 إلى 3",

						"القدرة على التنظيم"

		});
		dmodel_0.addRow(new String[] {
						"", "", "من 0 إلى 4",

						"السلوك المهني"
		});
		dmodel_0.addRow(new String[] {
						"", "", "من 0 إلى 3",

						"البحث وَ الإبتكار"
		});
		dmodel_0.addRow(new String[] {
						"", "", "", " مجموع النقاط الجزئيّة"
		});

		frame = new JFrame( );
		frame.getContentPane( ).setBackground(Color.WHITE);
		frame.setBounds(100, 100, Printer.STD_WIDTH, Printer.STD_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane( ).setLayout(null);

		JLabel lblRoyaumeDuMaroc = new JLabel("Royaume du Maroc");
		lblRoyaumeDuMaroc.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblRoyaumeDuMaroc.setBounds(12, 12, 154, 15);
		frame.getContentPane( ).add(lblRoyaumeDuMaroc);

		JLabel lblNewLabel = new JLabel("Faculté des Lettres et des Sciences");
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblNewLabel.setBounds(12, 66, 263, 15);
		frame.getContentPane( ).add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Université Chouaib Doukkali");
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(12, 39, 202, 15);
		frame.getContentPane( ).add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Humaines - El Jadida");
		lblNewLabel_2.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(12, 97, 152, 15);
		frame.getContentPane( ).add(lblNewLabel_2);

		JLabel label = new JLabel("المملكة المغربية");
		label.setFont(new Font("Arial", Font.BOLD, 16));
		label.setBounds(619, 12, 86, 15);
		frame.getContentPane( ).add(label);

		JLabel label_1 = new JLabel("جامعة شعيب دكالي");
		label_1.setFont(new Font("Arial", Font.BOLD, 16));
		label_1.setBounds(603, 39, 102, 15);
		frame.getContentPane( ).add(label_1);

		JLabel label_2 = new JLabel("كلية الآدب والعلوم الإنسانية");
		label_2.setFont(new Font("Arial", Font.BOLD, 16));
		label_2.setBounds(561, 70, 144, 15);
		frame.getContentPane( ).add(label_2);

		JLabel label_3 = new JLabel("الجديدة");
		label_3.setFont(new Font("Arial", Font.BOLD, 16));
		label_3.setBounds(668, 97, 37, 15);
		frame.getContentPane( ).add(label_3);

		JLabel label_4 = new JLabel(
			"_____________________________________________________________________");
		label_4.setFont(new Font("Dialog", Font.BOLD, 17));
		label_4.setBounds(53, 93, 626, 33);
		frame.getContentPane( ).add(label_4);

		JLabel lblElJadidaLe = new JLabel(
			String.format("حرّر بالجديدة في:%s", fmt.format(new Date( ))));
		lblElJadidaLe.setFont(new Font("Arial", Font.PLAIN, 16));
		lblElJadidaLe.setBounds(539, 936, 172, 22);
		frame.getContentPane( ).add(lblElJadidaLe);

		JLabel lblAttestationDeTravail = new JLabel(String.format(
			"بطاقة التنقيط الفرديّة برسم سنة: %s",
			new SimpleDateFormat("yyyy").format(new Date( ))));
		lblAttestationDeTravail.setFont(new Font("Arial", Font.BOLD, 20));
		lblAttestationDeTravail.setBounds(235, 142, 263, 29);
		frame.getContentPane( ).add(lblAttestationDeTravail);

		JLabel label_5 = new JLabel("توقيع رئيس الإدارة أو السّلطة المفوض لها");
		label_5.setFont(new Font("Arial", Font.PLAIN, 16));
		label_5.setBounds(53, 940, 263, 15);
		frame.getContentPane( ).add(label_5);

		JPanel panel_1 = new JPanel( );
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_1.setBounds(21, 171, 690, 270);
		frame.getContentPane( ).add(panel_1);
		panel_1.setLayout(null);

		JPanel panel_5 = new JPanel( );
		panel_5.setBounds(0, 0, 690, 27);
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5.setBackground(Color.GRAY);
		panel_1.add(panel_5);
		panel_5.setLayout(null);

		JLabel label_6 = new JLabel("هويّة الموظف");
		label_6.setFont(new Font("Arial", Font.BOLD, 18));
		label_6.setBounds(571, 1, 90, 25);
		panel_5.add(label_6);

		JPanel panel = new JPanel( );
		panel.setBackground(Color.WHITE);
		panel.setBounds(5, 22, 680, 236);
		panel_1.add(panel);
		panel.setLayout(null);

		JLabel label_7 = new JLabel("الإسم الشخصي");
		label_7.setBounds(584, 14, 84, 19);
		panel.add(label_7);
		label_7.setFont(new Font("Arial", Font.BOLD, 16));

		JLabel label_8 = new JLabel("الإسم العائلي");
		label_8.setBounds(594, 47, 84, 19);
		panel.add(label_8);
		label_8.setFont(new Font("Arial", Font.BOLD, 16));

		JLabel label_9 = new JLabel("تاريخ الإزدياد");
		label_9.setBounds(593, 80, 75, 15);
		panel.add(label_9);
		label_9.setFont(new Font("Arial", Font.BOLD, 16));

		JLabel label_10 = new JLabel("متزوج؟");
		label_10.setBounds(625, 109, 43, 18);
		panel.add(label_10);
		label_10.setFont(new Font("Arial", Font.BOLD, 16));

		JLabel label_11 = new JLabel("عدد الأطفال");
		label_11.setBounds(287, 93, 63, 15);
		panel.add(label_11);
		label_11.setFont(new Font("Arial", Font.BOLD, 16));

		JLabel label_12 = new JLabel("مكان الإزدياد");
		label_12.setBounds(275, 66, 75, 15);
		panel.add(label_12);
		label_12.setFont(new Font("Arial", Font.BOLD, 16));

		JLabel label_13 = new JLabel("تاريخ التعيين في الدرجة:");
		label_13.setBounds(537, 173, 156, 18);
		panel.add(label_13);
		label_13.setFont(new Font("Arial", Font.BOLD, 16));

		JLabel lbl_grade = new JLabel("4");
		lbl_grade.setBounds(12, 122, 216, 15);
		panel.add(lbl_grade);
		lbl_grade.setFont(new Font("Arial", Font.BOLD, 16));

		JLabel lbl_since = new JLabel("24/05/2017");
		lbl_since.setBounds(12, 206, 216, 15);
		panel.add(lbl_since);
		lbl_since.setFont(new Font("Arial", Font.BOLD, 16));

		JLabel lbl_current_job = new JLabel(e.getCurrentJob( ));
		lbl_current_job.setBounds(12, 176, 216, 18);
		panel.add(lbl_current_job);
		lbl_current_job.setFont(new Font("Arial", Font.BOLD, 16));

		JLabel lbl_uplift_date = new JLabel("15/11/2016");
		lbl_uplift_date.setBounds(12, 149, 216, 15);
		panel.add(lbl_uplift_date);
		lbl_uplift_date.setFont(new Font("Arial", Font.BOLD, 16));

		JLabel label_18 = new JLabel("  رقم ب.ت.و.");
		label_18.setBounds(280, 12, 70, 15);
		panel.add(label_18);
		label_18.setFont(new Font("Arial", Font.BOLD, 16));

		JLabel label_19 = new JLabel("ر. التأجير");
		label_19.setBounds(293, 39, 57, 15);
		panel.add(label_19);
		label_19.setFont(new Font("Arial", Font.BOLD, 16));

		JLabel label_20 = new JLabel(
			"الإطار وَ مقر التعيين: بكلية الأدب، الجديدة");
		label_20.setBounds(455, 141, 225, 18);
		panel.add(label_20);
		label_20.setFont(new Font("Arial", Font.BOLD, 16));

		JLabel label_21 = new JLabel("تاريخ ولوج الوظيفة العمومية:");
		label_21.setBounds(510, 205, 185, 17);
		panel.add(label_21);
		label_21.setFont(new Font("Arial", Font.BOLD, 16));

		JLabel lbl_name = new JLabel(e.getName( ));
		lbl_name.setFont(new Font("Arial", Font.BOLD, 16));
		lbl_name.setBounds(370, 14, 145, 19);
		panel.add(lbl_name);

		JLabel lbl_fname = new JLabel(e.getFamilyname( ));
		lbl_fname.setFont(new Font("Arial", Font.BOLD, 16));
		lbl_fname.setBounds(370, 47, 145, 19);
		panel.add(lbl_fname);

		JLabel lbl_bday = new JLabel(fmt.format(e.getBirthDay( )));
		lbl_bday.setFont(new Font("Arial", Font.BOLD, 16));
		lbl_bday.setBounds(370, 80, 145, 15);
		panel.add(lbl_bday);

		JLabel lbl_ismarried = new JLabel(e.isMarried( ) ? "نعم" : "لا");
		lbl_ismarried.setFont(new Font("Arial", Font.BOLD, 16));
		lbl_ismarried.setBounds(370, 109, 145, 18);
		panel.add(lbl_ismarried);

		JLabel lbl_hdate = new JLabel(fmt.format(e.getHiringDate( )));
		lbl_hdate.setFont(new Font("Arial", Font.BOLD, 16));
		lbl_hdate.setBounds(370, 173, 145, 15);
		panel.add(lbl_hdate);

		JLabel lbl_jdate = new JLabel(fmt.format(e.getJoinDate( )));
		lbl_jdate.setFont(new Font("Arial", Font.BOLD, 16));
		lbl_jdate.setBounds(370, 202, 145, 18);
		panel.add(lbl_jdate);

		JLabel label_14 = new JLabel("الرتبة:");
		label_14.setFont(new Font("Arial", Font.BOLD, 16));
		label_14.setBounds(306, 120, 44, 15);
		panel.add(label_14);

		JLabel label_15 = new JLabel("تاريخ المفعول:");
		label_15.setFont(new Font("Arial", Font.BOLD, 16));
		label_15.setBounds(266, 147, 84, 15);
		panel.add(label_15);

		JLabel label_16 = new JLabel("الوظيفة المزاولة حاليا:");
		label_16.setFont(new Font("Arial", Font.BOLD, 16));
		label_16.setBounds(229, 174, 121, 18);
		panel.add(label_16);

		JLabel label_17 = new JLabel("منذ:");
		label_17.setFont(new Font("Arial", Font.BOLD, 16));
		label_17.setBounds(317, 204, 33, 15);
		panel.add(label_17);

		JLabel lbl_nchildren = new JLabel("" + e.getNumberOfChildren( ));
		lbl_nchildren.setFont(new Font("Arial", Font.BOLD, 16));
		lbl_nchildren.setBounds(12, 95, 216, 15);
		panel.add(lbl_nchildren);

		JLabel lbl_bplace = new JLabel(e.getBirthPlace( ));
		lbl_bplace.setFont(new Font("Arial", Font.BOLD, 16));
		lbl_bplace.setBounds(12, 68, 216, 15);
		panel.add(lbl_bplace);

		JLabel lbl_ref = new JLabel(e.getReference( ));
		lbl_ref.setFont(new Font("Arial", Font.BOLD, 16));
		lbl_ref.setBounds(12, 41, 216, 15);
		panel.add(lbl_ref);

		JLabel lbl_cin = new JLabel(e.getCIN( ));
		lbl_cin.setFont(new Font("Arial", Font.BOLD, 16));
		lbl_cin.setBounds(12, 14, 216, 15);
		panel.add(lbl_cin);

		JPanel panel_2 = new JPanel( );
		panel_2.setLayout(null);
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(21, 440, 690, 161);
		frame.getContentPane( ).add(panel_2);

		JPanel panel_3 = new JPanel( );
		panel_3.setLayout(null);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBackground(Color.GRAY);
		panel_3.setBounds(0, 0, 690, 27);
		panel_2.add(panel_3);

		JLabel label_22 = new JLabel("النقطة الممنوحة");
		label_22.setFont(new Font("Arial", Font.BOLD, 18));
		label_22.setBounds(564, 1, 97, 25);
		panel_3.add(label_22);

		JPanel panel_4 = new JPanel( );
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(0, 25, 690, 136);
		panel_2.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane( );
		panel_4.add(scrollPane, BorderLayout.CENTER);

		JPanel panel_6 = new JPanel( );
		panel_6.setLayout(null);
		panel_6.setBackground(Color.WHITE);
		panel_6.setBounds(21, 594, 690, 92);
		frame.getContentPane( ).add(panel_6);

		JPanel panel_7 = new JPanel( );
		panel_7.setLayout(null);
		panel_7.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_7.setBackground(Color.GRAY);
		panel_7.setBounds(0, 0, 690, 36);
		panel_6.add(panel_7);

		JLabel label_23 = new JLabel("الميزة الممنوحة");
		label_23.setFont(new Font("Arial", Font.BOLD, 18));
		label_23.setBounds(571, 8, 90, 19);
		panel_7.add(label_23);

		JPanel panel_8 = new JPanel( );
		panel_8.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_8.setBackground(Color.WHITE);
		panel_8.setBounds(0, 36, 690, 56);
		panel_6.add(panel_8);
		panel_8.setLayout(null);

		JCheckBox chckbx4 = new JCheckBox("ممتاز");
		chckbx4.setFont(new Font("Arial", Font.BOLD, 16));
		chckbx4.setBackground(Color.WHITE);
		chckbx4.setBounds(564, 8, 63, 23);
		panel_8.add(chckbx4);

		JCheckBox chck3 = new JCheckBox("جيّد جداً");
		chck3.setFont(new Font("Arial", Font.BOLD, 16));
		chck3.setBackground(Color.WHITE);
		chck3.setBounds(431, 8, 70, 23);
		panel_8.add(chck3);

		JCheckBox chck2 = new JCheckBox("جيّد");
		chck2.setFont(new Font("Arial", Font.BOLD, 16));
		chck2.setBackground(Color.WHITE);
		chck2.setBounds(322, 8, 46, 23);
		panel_8.add(chck2);

		JCheckBox chck1 = new JCheckBox("متوسّط");
		chck1.setFont(new Font("Arial", Font.BOLD, 16));
		chck1.setBackground(Color.WHITE);
		chck1.setBounds(189, 8, 70, 23);
		panel_8.add(chck1);

		JCheckBox chck0 = new JCheckBox("ضعيف");
		chck0.setFont(new Font("Arial", Font.BOLD, 16));
		chck0.setBackground(Color.WHITE);
		chck0.setBounds(64, 8, 63, 23);
		panel_8.add(chck0);

		JLabel lblNewLabel_3 = new JLabel("(من 18 إلى 20)");
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_3.setBackground(Color.WHITE);
		lblNewLabel_3.setBounds(549, 39, 93, 15);
		panel_8.add(lblNewLabel_3);

		JLabel label_24 = new JLabel("(من 16 إلى 18)");
		label_24.setFont(new Font("Arial", Font.PLAIN, 14));
		label_24.setBackground(Color.WHITE);
		label_24.setBounds(420, 39, 93, 15);
		panel_8.add(label_24);

		JLabel label_25 = new JLabel("(من 14 إلى 16)");
		label_25.setFont(new Font("Arial", Font.PLAIN, 14));
		label_25.setBackground(Color.WHITE);
		label_25.setBounds(299, 39, 93, 15);
		panel_8.add(label_25);

		JLabel label_26 = new JLabel("(من 10 إلى 14)");
		label_26.setFont(new Font("Arial", Font.PLAIN, 14));
		label_26.setBackground(Color.WHITE);
		label_26.setBounds(178, 39, 93, 15);
		panel_8.add(label_26);

		JLabel label_27 = new JLabel("< 10");
		label_27.setFont(new Font("Arial", Font.PLAIN, 14));
		label_27.setBackground(Color.WHITE);
		label_27.setBounds(74, 39, 93, 15);
		panel_8.add(label_27);

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

		table = new JTable(dmodel_0);
		new TableCellListener(table, new AbstractAction( ) {
			private static final long serialVersionUID = 1L;

			private int getTotal(JTable table) {
				int s = 0;
				String str;

				// last row is not important
				for (int i = 0; i < table.getRowCount( ) - 1; i++) {
					try {
						str = table.getValueAt(i, INDEX_NOTE_COL).toString( );
						s += Integer.parseInt(str.isEmpty( ) ? "0" : str);
					} catch (Exception e2) {
						s += 0;
					}
				}
				return s;
			}

			private void enableCheckBox(int value) {
				int index = 0;

				if (value < 10) {
					index = 0;
				} else if (value >= 10 && value < 14) {
					index = 1;
				} else if (value >= 14 && value < 16) {
					index = 2;
				} else if (value >= 16 && value < 18) {
					index = 3;
				} else {
					index = 4;
				}

				JCheckBox chks[] = new JCheckBox[] {
								chck0, chck1, chck2, chck3, chckbx4
				};

				for (int i = 0; i < chks.length; i++) {
					chks[i].setSelected(i == index);
				}
			}

			public void actionPerformed(ActionEvent e) {
				TableCellListener tcl = (TableCellListener) e.getSource( );
				if (tcl.getColumn( ) == 1) {
					JTable t = tcl.getTable( );

					enableCheckBox(getTotal(t));
					t.setValueAt(getTotal(t), INDEX_TOTAL_ROW, INDEX_NOTE_COL);
				}
			}
		});
		table.setRowSelectionAllowed(false);
		table.setFont(new Font("Arial", Font.BOLD, 16));
		scrollPane.setViewportView(table);

	}
}
