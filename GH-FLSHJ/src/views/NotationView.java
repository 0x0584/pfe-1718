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
import app.utils.DateUtil;
import app.utils.JTableCellListener;
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
		// frame.setBounds(100, 100, Printer.A4_STD_WIDTH,
		// Printer.A4_STD_HEIGHT);
		frame.setBounds(100, 100, 733, 1054);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane( ).setLayout(null);

		JLabel lblRoyaumeDuMaroc = new JLabel("Royaume du Maroc");
		lblRoyaumeDuMaroc.setFont(new Font("Arial", Font.BOLD, 14));
		lblRoyaumeDuMaroc.setBounds(12, 12, 154, 15);
		frame.getContentPane( ).add(lblRoyaumeDuMaroc);

		JLabel lblNewLabel = new JLabel("Faculté des Lettres et des Sciences");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel.setBounds(12, 44, 263, 15);
		frame.getContentPane( ).add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Université Chouaib Doukkali");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_1.setBounds(12, 28, 202, 15);
		frame.getContentPane( ).add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Humaines - El Jadida");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_2.setBounds(12, 60, 152, 15);
		frame.getContentPane( ).add(lblNewLabel_2);

		JLabel label = new JLabel("المملكة المغربية");
		label.setFont(new Font("Arial", Font.BOLD, 16));
		label.setBounds(619, 12, 86, 15);
		frame.getContentPane( ).add(label);

		JLabel label_1 = new JLabel("جامعة شعيب دكالي");
		label_1.setFont(new Font("Arial", Font.BOLD, 16));
		label_1.setBounds(603, 28, 102, 15);
		frame.getContentPane( ).add(label_1);

		JLabel label_2 = new JLabel("كلية الآدب والعلوم الإنسانية");
		label_2.setFont(new Font("Arial", Font.BOLD, 16));
		label_2.setBounds(561, 44, 144, 15);
		frame.getContentPane( ).add(label_2);

		JLabel label_3 = new JLabel("الجديدة");
		label_3.setFont(new Font("Arial", Font.BOLD, 16));
		label_3.setBounds(668, 60, 37, 15);
		frame.getContentPane( ).add(label_3);

		JLabel label_4 = new JLabel(
			"_____________________________________________________________________");
		label_4.setFont(new Font("Dialog", Font.BOLD, 17));
		label_4.setBounds(53, 62, 626, 33);
		frame.getContentPane( ).add(label_4);

		JLabel lblElJadidaLe = new JLabel(
			String.format("حرّر بالجديدة في:%s", fmt.format(new Date( ))));
		lblElJadidaLe.setFont(new Font("Arial", Font.BOLD, 16));
		lblElJadidaLe.setBounds(533, 727, 172, 22);
		frame.getContentPane( ).add(lblElJadidaLe);

		JLabel lblAttestationDeTravail = new JLabel(String.format(
			"بطاقة التنقيط الفرديّة برسم سنة: %s",
			new SimpleDateFormat("yyyy").format(new Date( ))));
		lblAttestationDeTravail.setFont(new Font("Arial", Font.BOLD, 20));
		lblAttestationDeTravail.setBounds(235, 91, 263, 29);
		frame.getContentPane( ).add(lblAttestationDeTravail);

		JLabel label_5 = new JLabel("توقيع رئيس الإدارة أو السّلطة المفوض لها");
		label_5.setFont(new Font("Arial", Font.BOLD, 16));
		label_5.setBounds(53, 731, 263, 15);
		frame.getContentPane( ).add(label_5);

		JPanel panel_1 = new JPanel( );
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_1.setBounds(15, 123, 690, 186);
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
		panel.setBounds(5, 22, 680, 159);
		panel_1.add(panel);
		panel.setLayout(null);

		JLabel label_7 = new JLabel("الإسم الشخصي");
		label_7.setBounds(584, 4, 84, 19);
		panel.add(label_7);
		label_7.setFont(new Font("Arial", Font.BOLD, 16));

		JLabel label_8 = new JLabel("الإسم العائلي");
		label_8.setBounds(593, 27, 75, 19);
		panel.add(label_8);
		label_8.setFont(new Font("Arial", Font.BOLD, 16));

		JLabel label_9 = new JLabel("تاريخ الإزدياد");
		label_9.setBounds(593, 50, 75, 15);
		panel.add(label_9);
		label_9.setFont(new Font("Arial", Font.BOLD, 16));

		JLabel label_10 = new JLabel("متزوج؟");
		label_10.setBounds(625, 69, 43, 18);
		panel.add(label_10);
		label_10.setFont(new Font("Arial", Font.BOLD, 16));

		JLabel label_11 = new JLabel("عدد الأطفال");
		label_11.setBounds(287, 61, 63, 15);
		panel.add(label_11);
		label_11.setFont(new Font("Arial", Font.BOLD, 16));

		JLabel label_12 = new JLabel("مكان الإزدياد");
		label_12.setBounds(275, 42, 75, 15);
		panel.add(label_12);
		label_12.setFont(new Font("Arial", Font.BOLD, 16));

		JLabel label_13 = new JLabel("تاريخ التعيين في الدرجة:");
		label_13.setBounds(531, 113, 137, 18);
		panel.add(label_13);
		label_13.setFont(new Font("Arial", Font.BOLD, 16));

		JLabel lbl_grade = new JLabel("4");
		lbl_grade.setBounds(12, 80, 216, 15);
		panel.add(lbl_grade);
		lbl_grade.setFont(new Font("Arial", Font.BOLD, 16));

		JLabel lbl_since = new JLabel(DateUtil.parseDate(e.getJoinDate( )));
		lbl_since.setBounds(12, 140, 216, 15);
		panel.add(lbl_since);
		lbl_since.setFont(new Font("Arial", Font.BOLD, 16));

		JLabel lbl_current_job = new JLabel(e.getMission( ));
		lbl_current_job.setBounds(12, 118, 216, 18);
		panel.add(lbl_current_job);
		lbl_current_job.setFont(new Font("Arial", Font.BOLD, 16));

		JLabel lbl_uplift_date = new JLabel(
			DateUtil.parseDate(e.getCurrentUplift( ).getDate( )));
		lbl_uplift_date.setBounds(12, 99, 216, 15);
		panel.add(lbl_uplift_date);
		lbl_uplift_date.setFont(new Font("Arial", Font.BOLD, 16));

		JLabel label_18 = new JLabel("  رقم ب.ت.و.");
		label_18.setBounds(280, 4, 70, 15);
		panel.add(label_18);
		label_18.setFont(new Font("Arial", Font.BOLD, 16));

		JLabel label_19 = new JLabel("ر. التأجير");
		label_19.setBounds(293, 23, 57, 15);
		panel.add(label_19);
		label_19.setFont(new Font("Arial", Font.BOLD, 16));

		JLabel label_20 = new JLabel("الإطار:");
		label_20.setBounds(623, 91, 45, 18);
		panel.add(label_20);
		label_20.setFont(new Font("Arial", Font.BOLD, 16));

		JLabel label_21 = new JLabel("تاريخ ولوج الوظيفة العمومية:");
		label_21.setBounds(504, 135, 164, 17);
		panel.add(label_21);
		label_21.setFont(new Font("Arial", Font.BOLD, 16));

		String ar_name = "";
		try {
			ar_name = e.getNameArabic( );
		} catch (Exception x) {
			ar_name = e.getName( );
		}

		JLabel lbl_name = new JLabel(ar_name);
		lbl_name.setFont(new Font("Arial", Font.BOLD, 16));
		lbl_name.setBounds(370, 4, 145, 19);
		panel.add(lbl_name);

		String f_name = "";
		try {
			f_name = e.getFamilyNameArabic( );
		} catch (Exception x) {
			f_name = e.getFamilyName( );
		}

		JLabel lbl_fname = new JLabel(f_name);
		lbl_fname.setFont(new Font("Arial", Font.BOLD, 16));
		lbl_fname.setBounds(370, 27, 145, 19);
		panel.add(lbl_fname);

		JLabel lbl_bday = new JLabel(fmt.format(e.getBirthDay( )));
		lbl_bday.setFont(new Font("Arial", Font.BOLD, 16));
		lbl_bday.setBounds(370, 50, 145, 15);
		panel.add(lbl_bday);

		JLabel lbl_ismarried = new JLabel(e.isMarried( ));
		lbl_ismarried.setFont(new Font("Arial", Font.BOLD, 16));
		lbl_ismarried.setBounds(370, 69, 145, 18);
		panel.add(lbl_ismarried);

		JLabel lbl_hdate = new JLabel(fmt.format(e.getHiringDate( )));
		lbl_hdate.setFont(new Font("Arial", Font.BOLD, 16));
		lbl_hdate.setBounds(370, 113, 145, 15);
		panel.add(lbl_hdate);

		JLabel lbl_jdate = new JLabel(fmt.format(e.getJoinDate( )));
		lbl_jdate.setFont(new Font("Arial", Font.BOLD, 16));
		lbl_jdate.setBounds(370, 132, 145, 18);
		panel.add(lbl_jdate);

		JLabel label_14 = new JLabel("الرتبة:");
		label_14.setFont(new Font("Arial", Font.BOLD, 16));
		label_14.setBounds(306, 80, 44, 15);
		panel.add(label_14);

		JLabel label_15 = new JLabel("تاريخ المفعول:");
		label_15.setFont(new Font("Arial", Font.BOLD, 16));
		label_15.setBounds(266, 99, 84, 15);
		panel.add(label_15);

		JLabel label_16 = new JLabel("الوظيفة المزاولة حاليا:");
		label_16.setFont(new Font("Arial", Font.BOLD, 16));
		label_16.setBounds(229, 118, 121, 18);
		panel.add(label_16);

		JLabel label_17 = new JLabel("منذ:");
		label_17.setFont(new Font("Arial", Font.BOLD, 16));
		label_17.setBounds(317, 140, 33, 15);
		panel.add(label_17);

		JLabel lbl_nchildren = new JLabel("" + e.getNumberOfChildren( ));
		lbl_nchildren.setFont(new Font("Arial", Font.BOLD, 16));
		lbl_nchildren.setBounds(12, 61, 216, 15);
		panel.add(lbl_nchildren);

		String bplace_ar = "";
		try {
			bplace_ar = e.getBirthPlaceArabic( );
		} catch (Exception x) {
			bplace_ar = e.getBirthPlace( );
		}

		JLabel lbl_bplace = new JLabel(bplace_ar);
		lbl_bplace.setFont(new Font("Arial", Font.BOLD, 16));
		lbl_bplace.setBounds(12, 42, 216, 15);
		panel.add(lbl_bplace);

		JLabel lbl_ref = new JLabel(e.getEmployeeReference( ));
		lbl_ref.setFont(new Font("Arial", Font.BOLD, 16));
		lbl_ref.setBounds(12, 23, 216, 15);
		panel.add(lbl_ref);

		JLabel lbl_cin = new JLabel(e.getCIN( ));
		lbl_cin.setFont(new Font("Arial", Font.BOLD, 16));
		lbl_cin.setBounds(12, 4, 216, 15);
		panel.add(lbl_cin);

		JLabel label_28 = new JLabel(e.getCadre( ).toString( ));
		label_28.setFont(new Font("Arial", Font.BOLD, 16));
		label_28.setBounds(370, 91, 253, 18);
		panel.add(label_28);

		JPanel panel_2 = new JPanel( );
		panel_2.setLayout(null);
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(15, 301, 690, 161);
		frame.getContentPane( ).add(panel_2);

		JPanel panel_3 = new JPanel( );
		panel_3.setLayout(null);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBackground(Color.GRAY);
		panel_3.setBounds(0, 3, 690, 27);
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
		scrollPane.setLocation(1, 1);
		panel_4.add(scrollPane, BorderLayout.CENTER);

		JPanel panel_6 = new JPanel( );
		panel_6.setLayout(null);
		panel_6.setBackground(Color.WHITE);
		panel_6.setBounds(15, 455, 690, 70);
		frame.getContentPane( ).add(panel_6);

		JPanel panel_7 = new JPanel( );
		panel_7.setLayout(null);
		panel_7.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_7.setBackground(Color.GRAY);
		panel_7.setBounds(0, 0, 690, 24);
		panel_6.add(panel_7);

		JLabel label_23 = new JLabel("الميزة الممنوحة");
		label_23.setFont(new Font("Arial", Font.BOLD, 18));
		label_23.setBounds(571, 8, 90, 19);
		panel_7.add(label_23);

		JPanel panel_8 = new JPanel( );
		panel_8.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_8.setBackground(Color.WHITE);
		panel_8.setBounds(0, 23, 690, 56);
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
		lblNewLabel_3.setBounds(549, 32, 93, 15);
		panel_8.add(lblNewLabel_3);

		JLabel label_24 = new JLabel("(من 16 إلى 18)");
		label_24.setFont(new Font("Arial", Font.PLAIN, 14));
		label_24.setBackground(Color.WHITE);
		label_24.setBounds(420, 32, 93, 15);
		panel_8.add(label_24);

		JLabel label_25 = new JLabel("(من 14 إلى 16)");
		label_25.setFont(new Font("Arial", Font.PLAIN, 14));
		label_25.setBackground(Color.WHITE);
		label_25.setBounds(299, 32, 93, 15);
		panel_8.add(label_25);

		JLabel label_26 = new JLabel("(من 10 إلى 14)");
		label_26.setFont(new Font("Arial", Font.PLAIN, 14));
		label_26.setBackground(Color.WHITE);
		label_26.setBounds(178, 32, 93, 15);
		panel_8.add(label_26);

		JLabel label_27 = new JLabel("(< 10)");
		label_27.setFont(new Font("Arial", Font.PLAIN, 14));
		label_27.setBackground(Color.WHITE);
		label_27.setBounds(74, 32, 93, 15);
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

		JPanel panel_12 = new JPanel( );
		panel_12.setLayout(null);
		panel_12.setBackground(Color.WHITE);
		panel_12.setBounds(15, 643, 690, 70);
		frame.getContentPane( ).add(panel_12);

		JPanel panel_14 = new JPanel( );
		panel_14.setLayout(null);
		panel_14.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_14.setBackground(Color.WHITE);
		panel_14.setBounds(0, 36, 690, 34);
		panel_12.add(panel_14);

		JCheckBox checkfast = new JCheckBox("سريع");
		checkfast.setFont(new Font("Arial", Font.BOLD, 16));
		checkfast.setBackground(Color.WHITE);
		checkfast.setBounds(494, 8, 63, 23);
		panel_14.add(checkfast);

		JCheckBox checkmid = new JCheckBox("متوسط");
		checkmid.setFont(new Font("Arial", Font.BOLD, 16));
		checkmid.setBackground(Color.WHITE);
		checkmid.setBounds(241, 8, 70, 23);
		panel_14.add(checkmid);

		JCheckBox checkslow = new JCheckBox("بطيء");
		checkslow.setFont(new Font("Arial", Font.BOLD, 16));
		checkslow.setBackground(Color.WHITE);
		checkslow.setBounds(45, 8, 63, 23);
		panel_14.add(checkslow);

		table = new JTable(dmodel_0);
		new JTableCellListener(table, new AbstractAction( ) {
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

			private void enableCheckBoxes(int value) {
				int index_0 = 0, index_1 = 0;

				if (value < 10) {
					index_0 = index_1 = 0;
				} else if (value >= 10 && value < 14) {
					index_0 = index_1 = 1;
				} else if (value >= 14 && value < 16) {
					index_0 = 2;
					index_1 = 1;
				} else if (value >= 16 && value < 18) {
					index_0 = 3;
					index_1 = 2;
				} else {
					index_0 = 4;
					index_1 = 2;
				}

				JCheckBox chks_0[] = new JCheckBox[] {
								chck0, chck1, chck2, chck3, chckbx4
				};
				JCheckBox chks_1[] = new JCheckBox[] {
								checkslow, checkmid, checkfast
				};

				for (int i = 0; i < chks_0.length; i++) {
					chks_0[i].setSelected(i == index_0);
				}

				for (int i = 0; i < chks_1.length; i++) {
					chks_1[i].setSelected(i == index_1);
				}

			}

			public void actionPerformed(ActionEvent e) {
				JTableCellListener tcl = (JTableCellListener) e.getSource( );
				if (tcl.getColumn( ) == 1) {
					JTable t = tcl.getTable( );

					enableCheckBoxes(getTotal(t));
					t.setValueAt(getTotal(t), INDEX_TOTAL_ROW, INDEX_NOTE_COL);
				}
			}
		});
		table.setRowSelectionAllowed(false);
		table.setFont(new Font("Arial", Font.BOLD, 16));
		scrollPane.setViewportView(table);

		JPanel panel_9 = new JPanel( );
		panel_9.setLayout(null);
		panel_9.setBackground(Color.WHITE);
		panel_9.setBounds(15, 534, 690, 115);
		frame.getContentPane( ).add(panel_9);

		JPanel panel_10 = new JPanel( );
		panel_10.setLayout(null);
		panel_10.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_10.setBackground(Color.GRAY);
		panel_10.setBounds(0, 0, 690, 23);
		panel_9.add(panel_10);

		JLabel label_29 = new JLabel("معدل النقط المحصل عليها");
		label_29.setFont(new Font("Arial", Font.BOLD, 18));
		label_29.setBounds(503, 1, 158, 25);
		panel_10.add(label_29);

		JPanel panel_11 = new JPanel( );
		panel_11.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_11.setBackground(Color.WHITE);
		panel_11.setBounds(0, 25, 690, 90);
		panel_9.add(panel_11);
		panel_11.setLayout(null);

		JLabel lblNewLabel_4 = new JLabel(
			"تذكير بمعدل النقط المحصل عليها خلال السنوات المطلوبة للترقية في الرتبة");
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_4.setBounds(284, 5, 394, 15);
		panel_11.add(lblNewLabel_4);

		JLabel label_30 = new JLabel("نقطة السنة الأولى:");
		label_30.setFont(new Font("Arial", Font.BOLD, 16));
		label_30.setBounds(567, 22, 111, 15);
		panel_11.add(label_30);

		JLabel label_31 = new JLabel("نقطة السنة الثانية:");
		label_31.setFont(new Font("Arial", Font.BOLD, 16));
		label_31.setBounds(567, 46, 111, 15);
		panel_11.add(label_31);

		JLabel label_32 = new JLabel("نقطة لسنة:");
		label_32.setFont(new Font("Arial", Font.BOLD, 16));
		label_32.setBounds(216, 22, 69, 15);
		panel_11.add(label_32);

		JLabel label_33 = new JLabel("نقطة لسنة:");
		label_33.setFont(new Font("Arial", Font.BOLD, 16));
		label_33.setBounds(216, 46, 69, 15);
		panel_11.add(label_33);

		JLabel label_34 = new JLabel("معدل النقط المحصل عليها:      20/");
		label_34.setFont(new Font("Arial", Font.BOLD, 16));
		label_34.setBounds(484, 68, 194, 15);
		panel_11.add(label_34);

		JLabel label_36 = new JLabel("(> 16)");
		label_36.setFont(new Font("Arial", Font.PLAIN, 14));
		label_36.setBackground(Color.WHITE);
		label_36.setBounds(557, 13, 43, 15);
		panel_14.add(label_36);

		JLabel label_37 = new JLabel("(من 16 إلى 10)");
		label_37.setFont(new Font("Arial", Font.PLAIN, 14));
		label_37.setBackground(Color.WHITE);
		label_37.setBounds(310, 13, 93, 15);
		panel_14.add(label_37);

		JLabel label_40 = new JLabel("(< 10)");
		label_40.setFont(new Font("Arial", Font.PLAIN, 14));
		label_40.setBackground(Color.WHITE);
		label_40.setBounds(107, 13, 43, 15);
		panel_14.add(label_40);

		JPanel panel_13 = new JPanel( );
		panel_13.setLayout(null);
		panel_13.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_13.setBackground(Color.GRAY);
		panel_13.setBounds(0, 12, 690, 23);
		panel_12.add(panel_13);

		JLabel label_35 = new JLabel("نسق الترقية في الرتبة");
		label_35.setFont(new Font("Arial", Font.BOLD, 18));
		label_35.setBounds(519, 1, 142, 25);
		panel_13.add(label_35);

	}
}
