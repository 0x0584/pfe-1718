package wins;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import com.alee.laf.WebLookAndFeel;

import app.Cadre;
import app.utils.DateUtils;
import app.utils.XmlFile;
import model.Employee;
import model.Modeling;

public class InfoWin {

	private JFrame frame;

	public JFrame getFrame( ) {
		return frame;
	}

	private JTextField tf_name;
	private JTextField tf_fname;
	private JTextField tf_bplace;
	private JTextField tf_addr;
	private JTextField tf_phone;
	private JTextField tf_partner_name;
	private JTextField tf_partner_job;
	private JTextField tf_ref;
	private JTextField tf_date_hiring;
	private JTextField tf_finance;
	private JTextField tf_job_prev;
	private JTextField tf_job_current;
	private JTextField tf_date_join;
	private JTextField tf_mission;
	private JTable tbl_uplifts;
	private JTable tbl_diplomas;
	private JTextField tf_bdate;
	private JTextField tf_cin;
	private JTextField tf_d;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 * 
	 * TODO: get info
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable( ) {
			public void run( ) {
				try {

					InfoWin window = new InfoWin(new Employee( ));
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
	public InfoWin(Employee e) {
		initialize(e);
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @param empl
	 */
	private void initialize(Employee empl) {
		WebLookAndFeel.install( );

		frame = new JFrame("البطاقة الشخصية و الوضعية الإدارية");
		frame.setBounds(100, 100, 597, 614);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane( ).setLayout(null);

		JPanel panel = new JPanel( );
		panel.setBounds(12, 151, 571, 362);
		frame.getContentPane( ).add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setToolTipText("");
		panel.add(tabbedPane);

		JPanel panel_1 = new JPanel( );
		tabbedPane.addTab("البطاقة الشخصية", null, panel_1, null);
		panel_1.setLayout(null);

		JLabel label = new JLabel("الإسم الشخصي");
		label.setBounds(25, 11, 98, 15);
		panel_1.add(label);

		JLabel label_1 = new JLabel("الإسم العائلي");
		label_1.setBounds(32, 44, 84, 15);
		panel_1.add(label_1);

		JLabel label_2 = new JLabel("تاريخ الإزدياد");
		label_2.setBounds(32, 110, 84, 15);
		panel_1.add(label_2);

		JLabel label_3 = new JLabel("متزوج؟");
		label_3.setBounds(327, 44, 54, 15);
		panel_1.add(label_3);

		JLabel label_4 = new JLabel("إسم الزوج");
		label_4.setBounds(321, 77, 66, 15);
		panel_1.add(label_4);

		JLabel label_5 = new JLabel("مهنة الزوج");
		label_5.setBounds(317, 110, 75, 15);
		panel_1.add(label_5);

		JLabel lblChildren = new JLabel("عدد الأطفال");
		lblChildren.setBounds(317, 137, 75, 15);
		panel_1.add(lblChildren);

		tf_name = new JTextField(empl.getName( ));
		tf_name.setColumns(10);
		tf_name.setBounds(135, 4, 148, 29);
		panel_1.add(tf_name);

		JLabel label_7 = new JLabel("العنوان الشخصي");
		label_7.setBounds(19, 143, 111, 15);
		panel_1.add(label_7);

		JLabel label_8 = new JLabel("مكان الإزدياد");
		label_8.setBounds(30, 77, 89, 15);
		panel_1.add(label_8);

		JSpinner spin_nchildren = new JSpinner( );
		spin_nchildren.setModel(
			new SpinnerNumberModel(new Short((short) 0), new Short((short) 0),
				new Short((short) 10), new Short((short) 1)));
		spin_nchildren.setBounds(403, 136, 148, 29);
		panel_1.add(spin_nchildren);

		tf_fname = new JTextField(empl.getFamilyname( ));
		tf_fname.setColumns(10);
		tf_fname.setBounds(135, 37, 148, 29);
		panel_1.add(tf_fname);

		tf_bplace = new JTextField(empl.getBirthPlace( ));
		tf_bplace.setColumns(10);
		tf_bplace.setBounds(135, 70, 148, 29);
		panel_1.add(tf_bplace);

		tf_addr = new JTextField(empl.getAddress( ));
		tf_addr.setColumns(10);
		tf_addr.setBounds(135, 136, 148, 29);
		panel_1.add(tf_addr);

		tf_phone = new JTextField(empl.getPhone( ));
		tf_phone.setColumns(10);
		tf_phone.setBounds(403, 4, 148, 29);
		panel_1.add(tf_phone);

		tf_partner_name = new JTextField(empl.getPartnerName( ));
		tf_partner_name.setColumns(10);
		tf_partner_name.setBounds(403, 70, 148, 29);
		panel_1.add(tf_partner_name);

		tf_partner_job = new JTextField(empl.getPartnerJob( ));
		tf_partner_job.setColumns(10);
		tf_partner_job.setBounds(403, 103, 148, 29);
		// TODO: disable this
		panel_1.add(tf_partner_job);

		JRadioButton rd_ismarried_yes = new JRadioButton("نعم");
		rd_ismarried_yes.addChangeListener(new ChangeListener( ) {
			public void stateChanged(ChangeEvent e) {
				tf_partner_job.setEnabled(rd_ismarried_yes.isSelected( ));
				tf_partner_name.setEnabled(rd_ismarried_yes.isSelected( ));
				spin_nchildren.setEnabled(rd_ismarried_yes.isSelected( ));
			}
		});
		rd_ismarried_yes.setSelected(empl.isMarried( ));
		rd_ismarried_yes.setBounds(424, 42, 61, 19);
		panel_1.add(rd_ismarried_yes);

		JRadioButton rd_ismarried_no = new JRadioButton("لا");
		rd_ismarried_no.setSelected(false);
		rd_ismarried_no.setBounds(490, 42, 61, 19);
		panel_1.add(rd_ismarried_no);

		ButtonGroup bg = new ButtonGroup( );
		bg.add(rd_ismarried_yes);
		bg.add(rd_ismarried_no);

		JPanel panel_5 = new JPanel( );
		panel_5.setLayout(null);
		panel_5.setBounds(12, 170, 542, 153);
		panel_1.add(panel_5);

		JLabel label_6 = new JLabel("الشهادات");
		label_6.setBounds(90, 5, 61, 15);
		panel_5.add(label_6);

		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setBounds(0, 24, 542, 129);
		panel_5.add(scrollPane);

		tbl_diplomas = new JTable(Modeling.getDiplomasModel(empl));
		scrollPane.setViewportView(tbl_diplomas);

		JButton btndip = new JButton("تعديل");
		btndip.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				new DiplomaWin(empl).getFrame( ).setVisible(true);
			}
		});
		btndip.setBounds(425, 0, 117, 25);
		panel_5.add(btndip);

		tf_bdate = new JTextField(DateUtils.parseDate(empl.getBirthDay( )));
		tf_bdate.setColumns(10);
		tf_bdate.setBounds(135, 103, 148, 29);
		panel_1.add(tf_bdate);

		JLabel label_10 = new JLabel("الهاتف");
		label_10.setBounds(327, 11, 54, 15);
		panel_1.add(label_10);

		JPanel panel_2 = new JPanel( );
		tabbedPane.addTab("الوضعية الإدارية", null, panel_2, null);
		panel_2.setLayout(null);

		JLabel lblCadre = new JLabel("الإطار");
		lblCadre.setBounds(328, 10, 53, 15);
		panel_2.add(lblCadre);

		JLabel lblReference_1 = new JLabel("ر. المنصب المالي");
		lblReference_1.setBounds(15, 44, 114, 15);
		panel_2.add(lblReference_1);

		JLabel lblRentReference = new JLabel("ر. التأجير");
		lblRentReference.setBounds(36, 77, 72, 15);
		panel_2.add(lblRentReference);

		JLabel lblEmployment = new JLabel("تاريخ التوظيف");
		lblEmployment.setBounds(308, 44, 92, 15);
		panel_2.add(lblEmployment);

		JLabel lblJoined = new JLabel("تاريخ الإلتحاق بالكلية");
		lblJoined.setBounds(288, 77, 132, 15);
		panel_2.add(lblJoined);

		JLabel lblReason = new JLabel("سبب الإنتقال");
		lblReason.setBounds(315, 110, 81, 15);
		panel_2.add(lblReason);

		JLabel lblMission = new JLabel("المهمة");
		lblMission.setBounds(328, 143, 53, 15);
		panel_2.add(lblMission);

		JLabel lblPreviousJob = new JLabel("مقر العمل السابق ");
		lblPreviousJob.setBounds(12, 110, 121, 15);
		panel_2.add(lblPreviousJob);

		JComboBox<Cadre> comb_cadre = new JComboBox<Cadre>( );
		comb_cadre.setModel(new DefaultComboBoxModel<Cadre>(Cadre.values( )));
		comb_cadre.setBounds(410, 3, 148, 29);
		panel_2.add(comb_cadre);

		JComboBox<String> comb_reason = new JComboBox<String>( );
		comb_reason.setModel(new DefaultComboBoxModel<String>(new String[] {
						"إنتقال", "مبارة"
		}));
		comb_reason.setBounds(410, 103, 148, 29);
		panel_2.add(comb_reason);

		JLabel lblCurrentJob = new JLabel("مقر العمل الحالي");
		lblCurrentJob.setBounds(17, 143, 111, 15);
		panel_2.add(lblCurrentJob);

		tf_ref = new JTextField(empl.getReference( ));
		tf_ref.setColumns(10);
		tf_ref.setBounds(128, 70, 148, 29);
		panel_2.add(tf_ref);

		tf_date_hiring = new JTextField(
			DateUtils.parseDate(empl.getHiringDate( )));
		tf_date_hiring.setColumns(10);
		tf_date_hiring.setBounds(410, 37, 148, 29);
		panel_2.add(tf_date_hiring);

		tf_finance = new JTextField(empl.getFinancialStatus( ));
		tf_finance.setColumns(10);
		tf_finance.setBounds(128, 37, 148, 29);
		panel_2.add(tf_finance);

		tf_job_prev = new JTextField(empl.getPreviousJob( ));
		tf_job_prev.setColumns(10);
		tf_job_prev.setBounds(128, 103, 148, 29);
		panel_2.add(tf_job_prev);

		tf_job_current = new JTextField(empl.getCurrentJob( ));
		tf_job_current.setColumns(10);
		tf_job_current.setBounds(128, 136, 148, 29);
		panel_2.add(tf_job_current);

		tf_date_join = new JTextField(DateUtils.parseDate(empl.getJoinDate( )));
		tf_date_join.setColumns(10);
		tf_date_join.setBounds(410, 70, 148, 29);
		panel_2.add(tf_date_join);

		tf_mission = new JTextField(empl.getMission( ));
		tf_mission.setColumns(10);
		tf_mission.setBounds(410, 136, 148, 29);
		panel_2.add(tf_mission);

		JPanel panel_6 = new JPanel( );
		panel_6.setLayout(null);
		panel_6.setBounds(12, 170, 542, 153);
		panel_2.add(panel_6);

		JLabel label_9 = new JLabel("الترقيات");
		label_9.setBounds(95, 5, 58, 15);
		panel_6.add(label_9);

		JScrollPane scrollPane_1 = new JScrollPane((Component) null);
		scrollPane_1.setBounds(0, 27, 542, 126);
		panel_6.add(scrollPane_1);

		tbl_uplifts = new JTable(Modeling.getUpliftModel(empl));
		scrollPane_1.setViewportView(tbl_uplifts);

		JButton btnup = new JButton("تعديل");
		btnup.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				new UpliftWin(empl).getFrame( ).setVisible(true);
			}
		});
		btnup.setBounds(425, 0, 117, 25);
		panel_6.add(btnup);

		tf_cin = new JTextField(empl.getCIN( ));
		tf_cin.setColumns(10);
		tf_cin.setBounds(128, 3, 148, 29);
		panel_2.add(tf_cin);

		JLabel label_11 = new JLabel("ب.ت.و.");
		label_11.setBounds(46, 10, 53, 15);
		panel_2.add(label_11);

		JPanel panel_7 = new JPanel( );
		tabbedPane.addTab("معلومات إضافية", null, panel_7, null);
		panel_7.setLayout(null);

		JRadioButton rd_isma_yes = new JRadioButton("نعم");
		rd_ismarried_yes.addChangeListener(new ChangeListener( ) {
			public void stateChanged(ChangeEvent e) {
				tf_partner_job.setEnabled(rd_isma_yes.isSelected( ));
				tf_partner_name.setEnabled(rd_isma_yes.isSelected( ));
				spin_nchildren.setEnabled(rd_isma_yes.isSelected( ));
			}
		});
		rd_isma_yes.setSelected(empl.isMoroccan( ));
		rd_isma_yes.setBounds(431, 17, 61, 19);
		panel_7.add(rd_isma_yes);

		JRadioButton rd_isma_no = new JRadioButton("لا");
		rd_isma_no.setSelected(false);
		rd_isma_no.setBounds(497, 17, 61, 19);
		panel_7.add(rd_isma_no);

		ButtonGroup bg_ma = new ButtonGroup( );
		bg_ma.add(rd_isma_yes);
		bg_ma.add(rd_isma_no);

		JLabel label_12 = new JLabel("مقر العمل السابق ");
		label_12.setBounds(12, 119, 121, 15);
		panel_7.add(label_12);

		JLabel label_13 = new JLabel("ر. التأجير");
		label_13.setBounds(36, 86, 72, 15);
		panel_7.add(label_13);

		JLabel label_14 = new JLabel("ر. المنصب المالي");
		label_14.setBounds(15, 53, 114, 15);
		panel_7.add(label_14);

		JLabel lblIsmoroccan = new JLabel("جنسيّة مغربية");
		lblIsmoroccan.setBounds(318, 19, 120, 15);
		panel_7.add(lblIsmoroccan);

		tf_d = new JTextField((String) null);
		tf_d.setColumns(10);
		tf_d.setBounds(128, 12, 148, 29);
		panel_7.add(tf_d);

		textField_1 = new JTextField((String) null);
		textField_1.setColumns(10);
		textField_1.setBounds(128, 46, 148, 29);
		panel_7.add(textField_1);

		textField_2 = new JTextField((String) null);
		textField_2.setColumns(10);
		textField_2.setBounds(128, 79, 148, 29);
		panel_7.add(textField_2);

		textField_3 = new JTextField((String) null);
		textField_3.setColumns(10);
		textField_3.setBounds(128, 112, 148, 29);
		panel_7.add(textField_3);

		textField_4 = new JTextField((String) null);
		textField_4.setColumns(10);
		textField_4.setBounds(128, 145, 148, 29);
		panel_7.add(textField_4);

		JLabel label_16 = new JLabel("مقر العمل الحالي");
		label_16.setBounds(17, 152, 111, 15);
		panel_7.add(label_16);

		JLabel lblDepartment = new JLabel("شعبة");
		lblDepartment.setBounds(12, 19, 44, 15);
		panel_7.add(lblDepartment);

		JPanel panel_3 = new JPanel( );
		panel_3.setBounds(12, 12, 571, 127);
		frame.getContentPane( ).add(panel_3);
		panel_3.setLayout(null);

		JLabel lblMrRchidAnas = new JLabel(String.format(
			"السيد %s, %s", empl.getName( ), empl.getFamilyname( )));
		lblMrRchidAnas.setBounds(308, 13, 251, 15);
		panel_3.add(lblMrRchidAnas);

		JLabel lblDiplomas = new JLabel("الملاحظات:");
		lblDiplomas.setBounds(308, 39, 76, 15);
		panel_3.add(lblDiplomas);

		JPanel panel_4 = new JPanel( );
		panel_4.setBackground(SystemColor.activeCaption);
		panel_4.setBounds(12, 12, 104, 103);
		panel_3.add(panel_4);
		panel_4.setLayout(null);

		JLabel lblNewLabel = new JLabel("(Image)");
		lblNewLabel.setForeground(SystemColor.text);
		lblNewLabel.setBackground(SystemColor.controlLtHighlight);
		lblNewLabel.setBounds(17, 12, 70, 15);
		panel_4.add(lblNewLabel);

		JTextPane tp_notes = new JTextPane( );
		tp_notes.setEditable(false);
		tp_notes.setText(empl.getNotes( ));
		tp_notes.setBounds(134, 66, 425, 49);
		panel_3.add(tp_notes);

		JLabel lblM = new JLabel(
			String.format("ب.ت.و.: %s", empl.getReference( )));
		lblM.setBounds(134, 13, 141, 15);
		panel_3.add(lblM);

		JLabel lblRef = new JLabel(String.format("ر.ت: %s", empl.getCIN( )));
		lblRef.setBounds(134, 39, 141, 15);
		panel_3.add(lblRef);

		JButton btnsave = new JButton("حفظ");
		btnsave.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				Employee nempl = new Employee(empl.getReference( ));
				nempl.setNotes(tp_notes.getText( ));
				nempl.setDepartment(tf_d.getText( ));

				nempl.setName(tf_name.getText( ));
				nempl.setFamilyname(tf_fname.getText( ));
				nempl.setIsMoroccan(rd_isma_yes.isSelected( ));
				nempl.setBirthDay(DateUtils.parseDate(tf_bdate.getText( )));

				nempl.setBirthPlace(tf_bplace.getText( ));
				nempl.setAddress(tf_addr.getText( ));
				nempl.setPhone(tf_phone.getText( ));
				nempl.setIsMarried(rd_ismarried_yes.isSelected( ));

				nempl.setNumberOfchildren(
					Short.parseShort(spin_nchildren.getValue( ).toString( )));

				nempl.setPartnerName(tf_partner_name.getText( ));
				nempl.setPartnerJob(tf_partner_job.getText( ));

				nempl.setReference(tf_ref.getText( ));
				nempl.setCIN(tf_cin.getText( ));
				nempl.setMission(tf_mission.getText( ));
				nempl.setJoinDate(DateUtils.parseDate(tf_date_join.getText( )));
				nempl.setHiringDate(
					DateUtils.parseDate(tf_date_hiring.getText( )));

				nempl.setPreviousJob(tf_job_prev.getText( ));
				nempl.setCurrentJob(tf_job_current.getText( ));
				nempl.setCadre(
					Cadre.parseCadre(
						comb_cadre.getSelectedItem( ).toString( )));
				nempl.setFinancialStatus(tf_finance.getText( ));

				XmlFile.updateEmployee(empl.getReference( ), nempl);
			}
		});
		btnsave.setBounds(505, 525, 80, 25);
		frame.getContentPane( ).add(btnsave);

		JButton button = new JButton("*");
		button.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				tbl_diplomas.setModel(Modeling.getDiplomasModel(empl));
				tbl_uplifts.setModel(Modeling.getUpliftModel(empl));
			}
		});
		button.setBounds(361, 525, 40, 25);
		frame.getContentPane( ).add(button);

		JButton btnnew = new JButton("جديد");
		btnnew.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				String str = "جديد";
				if (e.getActionCommand( ).compareTo(str) == 0) {
					btnnew.setText("حفظ");
					btnsave.setVisible(false);

					clearFields( );
				} else {
					btnnew.setText("جديد");
					Employee nempl = new Employee( );
					nempl.setNotes(tp_notes.getText( ));
					nempl.setDepartment(tf_d.getText( ));

					nempl.setName(tf_name.getText( ));
					nempl.setFamilyname(tf_fname.getText( ));
					nempl.setIsMoroccan(rd_isma_yes.isSelected( ));
					nempl.setBirthDay(DateUtils.parseDate(tf_bdate.getText( )));

					nempl.setBirthPlace(tf_bplace.getText( ));
					nempl.setAddress(tf_addr.getText( ));
					nempl.setPhone(tf_phone.getText( ));
					nempl.setIsMarried(rd_ismarried_yes.isSelected( ));

					nempl.setNumberOfchildren(
						Short.parseShort(
							spin_nchildren.getValue( ).toString( )));
					nempl.setPartnerName(tf_partner_name.getText( ));
					nempl.setPartnerJob(tf_partner_job.getText( ));
					String strr = tf_ref.getText( ).compareTo("") == 0 ? ""
									+ (XmlFile.getlastEmployeeReference( ) + 1)
									: tf_ref.getText( );
					nempl.setReference(strr);
					nempl.setCIN(tf_cin.getText( ));
					nempl.setMission(tf_mission.getText( ));
					nempl.setJoinDate(
						DateUtils.parseDate(tf_date_join.getText( )));
					nempl.setHiringDate(
						DateUtils.parseDate(tf_date_hiring.getText( )));

					nempl.setPreviousJob(tf_job_prev.getText( ));
					nempl.setCurrentJob(tf_job_current.getText( ));
					nempl.setCadre(
						Cadre.parseCadre(
							comb_cadre.getSelectedItem( ).toString( )));
					nempl.setFinancialStatus(tf_finance.getText( ));

					XmlFile.addEmployee(nempl);
					frame.dispose( );
				}
			}

			private void clearFields( ) {
				tp_notes.setText("");
				tf_addr.setText( "");
				tf_bdate.setText(DateUtils.parseDate(new Date( )));
				tf_bplace.setText("");
				tf_cin.setText("");
				tf_d.setText("");
				tf_date_hiring.setText(DateUtils.parseDate(new Date( )));
				tf_date_join.setText(DateUtils.parseDate(new Date( )));
				tf_finance.setText("");
				tf_fname.setText("");
				tf_job_current.setText("");
				tf_job_prev.setText("");
				tf_mission.setText("");
				tf_name.setText("");
				tf_partner_job.setText("");
				tf_partner_name.setText("");
				tf_phone.setText("");
				tf_ref.setText("");
				spin_nchildren.setValue(0);
				tbl_diplomas.setModel(new DefaultTableModel( ));
				tbl_uplifts.setModel(new DefaultTableModel( ));
				rd_isma_yes.setSelected(true);
				rd_ismarried_no.setSelected(true);
			}
		});
		btnnew.setBounds(413, 525, 80, 25);
		frame.getContentPane( ).add(btnnew);

	}
}
