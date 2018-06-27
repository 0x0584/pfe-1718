package wins.crud;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import com.alee.laf.WebLookAndFeel;

import app.utils.DateUtil;
import model.Diploma;
import model.Employee;
import model.Uplift;

public class InfoCrud {

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
	private JTextField tf_name_ar;
	private JTextField tf_fname_ar;
	private JTextField tf_addr_ar;
	private JTextField tf_bplace_ar;
	private JTextField tf_cadre;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable( ) {
			public void run( ) {
				try {

					InfoCrud window = new InfoCrud(new Employee( ));
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
	public InfoCrud(Employee e) {
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
		frame.setResizable(false);

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
		label.setFont(new Font("Arial", Font.BOLD, 15));
		label.setBounds(25, 11, 98, 15);
		panel_1.add(label);

		JLabel label_1 = new JLabel("الإسم العائلي");
		label_1.setFont(new Font("Arial", Font.BOLD, 15));
		label_1.setBounds(32, 44, 84, 15);
		panel_1.add(label_1);

		JLabel label_2 = new JLabel("تاريخ الإزدياد");
		label_2.setFont(new Font("Arial", Font.BOLD, 15));
		label_2.setBounds(32, 110, 84, 15);
		panel_1.add(label_2);

		JLabel label_3 = new JLabel("متزوج؟");
		label_3.setFont(new Font("Arial", Font.BOLD, 15));
		label_3.setBounds(327, 44, 54, 15);
		panel_1.add(label_3);

		JLabel label_4 = new JLabel("إسم الزوج");
		label_4.setFont(new Font("Arial", Font.BOLD, 15));
		label_4.setBounds(321, 77, 66, 15);
		panel_1.add(label_4);

		JLabel label_5 = new JLabel("مهنة الزوج");
		label_5.setFont(new Font("Arial", Font.BOLD, 15));
		label_5.setBounds(317, 110, 75, 15);
		panel_1.add(label_5);

		JLabel lblChildren = new JLabel("عدد الأطفال");
		lblChildren.setFont(new Font("Arial", Font.BOLD, 15));
		lblChildren.setBounds(317, 137, 75, 15);
		panel_1.add(lblChildren);

		tf_name = new JTextField(empl.getName( ));
		tf_name.setFont(new Font("Arial", Font.PLAIN, 15));
		tf_name.setColumns(10);
		tf_name.setBounds(135, 4, 148, 29);
		panel_1.add(tf_name);

		JLabel label_7 = new JLabel("العنوان الشخصي");
		label_7.setFont(new Font("Arial", Font.BOLD, 15));
		label_7.setBounds(19, 143, 111, 15);
		panel_1.add(label_7);

		JLabel label_8 = new JLabel("مكان الإزدياد");
		label_8.setFont(new Font("Arial", Font.BOLD, 15));
		label_8.setBounds(30, 77, 89, 15);
		panel_1.add(label_8);

		JSpinner spin_nchildren = new JSpinner( );
		spin_nchildren.setFont(new Font("Arial", Font.BOLD, 13));
		spin_nchildren.setModel(
			new SpinnerNumberModel(new Short((short) 0), new Short((short) 0),
				new Short((short) 10), new Short((short) 1)));
		spin_nchildren.setBounds(403, 136, 148, 29);
		panel_1.add(spin_nchildren);

		tf_fname = new JTextField(empl.getFamilyName( ));
		tf_fname.setFont(new Font("Arial", Font.PLAIN, 15));
		tf_fname.setColumns(10);
		tf_fname.setBounds(135, 37, 148, 29);
		panel_1.add(tf_fname);

		tf_bplace = new JTextField(empl.getBirthPlace( ));
		tf_bplace.setFont(new Font("Arial", Font.PLAIN, 15));
		tf_bplace.setColumns(10);
		tf_bplace.setBounds(135, 70, 148, 29);
		panel_1.add(tf_bplace);

		tf_addr = new JTextField(empl.getAddress( ));
		tf_addr.setFont(new Font("Arial", Font.PLAIN, 15));
		tf_addr.setColumns(10);
		tf_addr.setBounds(135, 136, 148, 29);
		panel_1.add(tf_addr);

		tf_phone = new JTextField(empl.getPhone( ));
		tf_phone.setFont(new Font("Arial", Font.PLAIN, 15));
		tf_phone.setColumns(10);
		tf_phone.setBounds(403, 4, 148, 29);
		panel_1.add(tf_phone);

		tf_partner_name = new JTextField(empl.getPartnerName( ));
		tf_partner_name.setFont(new Font("Arial", Font.PLAIN, 15));
		tf_partner_name.setColumns(10);
		tf_partner_name.setBounds(403, 70, 148, 29);
		panel_1.add(tf_partner_name);

		tf_partner_job = new JTextField(empl.getPartnerJob( ));
		tf_partner_job.setFont(new Font("Arial", Font.PLAIN, 15));
		tf_partner_job.setColumns(10);
		tf_partner_job.setBounds(403, 103, 148, 29);
		panel_1.add(tf_partner_job);

		JRadioButton rd_ismarried_yes = new JRadioButton("نعم");
		rd_ismarried_yes.setFont(new Font("Arial", Font.BOLD, 15));
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
		rd_ismarried_no.setFont(new Font("Arial", Font.BOLD, 15));
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
		label_6.setFont(new Font("Arial", Font.BOLD, 15));
		label_6.setBounds(90, 5, 61, 15);
		panel_5.add(label_6);

		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setBounds(0, 24, 542, 129);
		panel_5.add(scrollPane);

		tbl_diplomas = new JTable(Diploma.getDiplomasModel(empl));
		tbl_diplomas.setFont(new Font("Arial", Font.PLAIN, 16));
		scrollPane.setViewportView(tbl_diplomas);

		JButton btndip = new JButton("تعديل");
		btndip.setFont(new Font("Arial", Font.BOLD, 15));
		btndip.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				new DiplomaCrud(empl).getFrame( ).setVisible(true);
			}
		});
		btndip.setBounds(425, 0, 117, 25);
		panel_5.add(btndip);

		tf_bdate = new JTextField(DateUtil.parseDate(empl.getBirthDay( )));
		tf_bdate.setFont(new Font("Arial", Font.PLAIN, 15));
		tf_bdate.setColumns(10);
		tf_bdate.setBounds(135, 103, 148, 29);
		panel_1.add(tf_bdate);

		JLabel label_10 = new JLabel("الهاتف");
		label_10.setFont(new Font("Arial", Font.BOLD, 15));
		label_10.setBounds(327, 11, 54, 15);
		panel_1.add(label_10);

		JPanel panel_2 = new JPanel( );
		tabbedPane.addTab("الوضعية الإدارية", null, panel_2, null);
		panel_2.setLayout(null);

		JLabel lblCadre = new JLabel("الإطار");
		lblCadre.setFont(new Font("Arial", Font.BOLD, 15));
		lblCadre.setBounds(328, 10, 53, 15);
		panel_2.add(lblCadre);

		JLabel lblReference_1 = new JLabel("ر. المنصب المالي");
		lblReference_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblReference_1.setBounds(15, 44, 114, 15);
		panel_2.add(lblReference_1);

		JLabel lblRentReference = new JLabel("ر. التأجير");
		lblRentReference.setFont(new Font("Arial", Font.BOLD, 15));
		lblRentReference.setBounds(36, 77, 72, 15);
		panel_2.add(lblRentReference);

		JLabel lblEmployment = new JLabel("تاريخ التوظيف");
		lblEmployment.setFont(new Font("Arial", Font.BOLD, 15));
		lblEmployment.setBounds(308, 44, 92, 15);
		panel_2.add(lblEmployment);

		JLabel lblJoined = new JLabel("تاريخ الإلتحاق بالكلية");
		lblJoined.setFont(new Font("Arial", Font.BOLD, 15));
		lblJoined.setBounds(288, 77, 132, 15);
		panel_2.add(lblJoined);

		JLabel lblReason = new JLabel("سبب الإنتقال");
		lblReason.setFont(new Font("Arial", Font.BOLD, 15));
		lblReason.setBounds(315, 110, 81, 15);
		panel_2.add(lblReason);

		JLabel lblMission = new JLabel("المهمة");
		lblMission.setFont(new Font("Arial", Font.BOLD, 15));
		lblMission.setBounds(328, 143, 53, 15);
		panel_2.add(lblMission);

		JLabel lblPreviousJob = new JLabel("مقر العمل السابق ");
		lblPreviousJob.setFont(new Font("Arial", Font.BOLD, 15));
		lblPreviousJob.setBounds(12, 110, 121, 15);
		panel_2.add(lblPreviousJob);

		JLabel lblCurrentJob = new JLabel("مقر العمل الحالي");
		lblCurrentJob.setFont(new Font("Arial", Font.BOLD, 15));
		lblCurrentJob.setBounds(17, 143, 111, 15);
		panel_2.add(lblCurrentJob);

		tf_ref = new JTextField(empl.getEmployeeReference( ));
		tf_ref.setFont(new Font("Arial", Font.PLAIN, 15));
		tf_ref.setColumns(10);
		tf_ref.setBounds(128, 70, 148, 29);
		panel_2.add(tf_ref);

		tf_date_hiring = new JTextField(
			DateUtil.parseDate(empl.getHiringDate( )));
		tf_date_hiring.setFont(new Font("Arial", Font.PLAIN, 15));
		tf_date_hiring.setColumns(10);
		tf_date_hiring.setBounds(410, 37, 148, 29);
		panel_2.add(tf_date_hiring);

		tf_finance = new JTextField(empl.getFinancialStatus( ));
		tf_finance.setFont(new Font("Arial", Font.PLAIN, 15));
		tf_finance.setColumns(10);
		tf_finance.setBounds(128, 37, 148, 29);
		panel_2.add(tf_finance);

		tf_job_prev = new JTextField(empl.getPreviousJob( ));
		tf_job_prev.setFont(new Font("Arial", Font.PLAIN, 15));
		tf_job_prev.setColumns(10);
		tf_job_prev.setBounds(128, 103, 148, 29);
		panel_2.add(tf_job_prev);

		tf_job_current = new JTextField(empl.getCurrentJob( ));
		tf_job_current.setFont(new Font("Arial", Font.PLAIN, 15));
		tf_job_current.setColumns(10);
		tf_job_current.setBounds(128, 136, 148, 29);
		panel_2.add(tf_job_current);

		tf_date_join = new JTextField(DateUtil.parseDate(empl.getJoinDate( )));
		tf_date_join.setFont(new Font("Arial", Font.PLAIN, 15));
		tf_date_join.setColumns(10);
		tf_date_join.setBounds(410, 70, 148, 29);
		panel_2.add(tf_date_join);

		tf_mission = new JTextField(empl.getMission( ));
		tf_mission.setFont(new Font("Arial", Font.PLAIN, 15));
		tf_mission.setColumns(10);
		tf_mission.setBounds(410, 136, 148, 29);
		panel_2.add(tf_mission);

		JPanel panel_6 = new JPanel( );
		panel_6.setLayout(null);
		panel_6.setBounds(12, 170, 542, 153);
		panel_2.add(panel_6);

		JLabel label_9 = new JLabel("الترقيات");
		label_9.setFont(new Font("Arial", Font.BOLD, 15));
		label_9.setBounds(95, 5, 58, 15);
		panel_6.add(label_9);

		JScrollPane scrollPane_1 = new JScrollPane((Component) null);
		scrollPane_1.setBounds(0, 27, 542, 126);
		panel_6.add(scrollPane_1);

		tbl_uplifts = new JTable(Uplift.getUpliftModel(empl));
		tbl_uplifts.setFont(new Font("Arial", Font.PLAIN, 15));
		scrollPane_1.setViewportView(tbl_uplifts);

		JButton btnup = new JButton("تعديل");
		btnup.setFont(new Font("Arial", Font.BOLD, 15));
		btnup.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				new UpliftCrud(empl).getFrame( ).setVisible(true);
			}
		});
		btnup.setBounds(425, 0, 117, 25);
		panel_6.add(btnup);

		tf_cin = new JTextField(empl.getCIN( ));
		tf_cin.setFont(new Font("Arial", Font.PLAIN, 15));
		tf_cin.setColumns(10);
		tf_cin.setBounds(128, 3, 148, 29);
		panel_2.add(tf_cin);

		JLabel label_11 = new JLabel("ب.ت.و.");
		label_11.setFont(new Font("Arial", Font.BOLD, 15));
		label_11.setBounds(46, 10, 53, 15);
		panel_2.add(label_11);

		tf_cadre = new JTextField("");
		tf_cadre.setFont(new Font("Arial", Font.PLAIN, 15));
		tf_cadre.setColumns(10);
		tf_cadre.setBounds(410, 8, 148, 29);
		panel_2.add(tf_cadre);

		textField_1 = new JTextField(empl.getReason( ));
		textField_1.setFont(new Font("Arial", Font.PLAIN, 15));
		textField_1.setColumns(10);
		textField_1.setBounds(410, 104, 148, 29);
		panel_2.add(textField_1);

		JPanel panel_7 = new JPanel( );
		tabbedPane.addTab("معلومات إضافية", null, panel_7, null);
		panel_7.setLayout(null);

		JRadioButton rd_isma_yes = new JRadioButton("نعم");
		rd_isma_yes.setFont(new Font("Arial", Font.BOLD, 15));
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
		rd_isma_no.setFont(new Font("Arial", Font.BOLD, 15));
		rd_isma_no.setSelected(false);
		rd_isma_no.setBounds(497, 17, 61, 19);
		panel_7.add(rd_isma_no);

		ButtonGroup bg_ma = new ButtonGroup( );
		bg_ma.add(rd_isma_yes);
		bg_ma.add(rd_isma_no);

		JLabel label_12 = new JLabel("العنوان بالعربية");
		label_12.setFont(new Font("Arial", Font.BOLD, 15));
		label_12.setBounds(104, 119, 105, 15);
		panel_7.add(label_12);

		JLabel label_13 = new JLabel("النسب بالعربية");
		label_13.setFont(new Font("Arial", Font.BOLD, 15));
		label_13.setBounds(107, 86, 96, 15);
		panel_7.add(label_13);

		JLabel label_14 = new JLabel("الإسم بالعربية");
		label_14.setFont(new Font("Arial", Font.BOLD, 15));
		label_14.setBounds(108, 53, 93, 15);
		panel_7.add(label_14);

		JLabel lblIsmoroccan = new JLabel("جنسيّة مغربية");
		lblIsmoroccan.setFont(new Font("Arial", Font.BOLD, 15));
		lblIsmoroccan.setBounds(318, 19, 120, 15);
		panel_7.add(lblIsmoroccan);

		tf_d = new JTextField(empl.getDepartment( ));
		tf_d.setFont(new Font("Arial", Font.PLAIN, 15));
		tf_d.setColumns(10);
		tf_d.setBounds(128, 12, 148, 29);
		panel_7.add(tf_d);

		tf_name_ar = new JTextField(empl.getNameArabic( ));
		tf_name_ar.setFont(new Font("Arial", Font.PLAIN, 15));
		tf_name_ar.setColumns(10);
		tf_name_ar.setBounds(309, 46, 148, 29);
		panel_7.add(tf_name_ar);

		tf_fname_ar = new JTextField(empl.getFamilyNameArabic( ));
		tf_fname_ar.setFont(new Font("Arial", Font.PLAIN, 15));
		tf_fname_ar.setColumns(10);
		tf_fname_ar.setBounds(309, 79, 148, 29);
		panel_7.add(tf_fname_ar);

		tf_addr_ar = new JTextField(empl.getAddressArabic( ));
		tf_addr_ar.setFont(new Font("Arial", Font.PLAIN, 15));
		tf_addr_ar.setColumns(10);
		tf_addr_ar.setBounds(309, 112, 148, 29);
		panel_7.add(tf_addr_ar);

		tf_bplace_ar = new JTextField(empl.getBirthPlaceArabic( ));
		tf_bplace_ar.setFont(new Font("Arial", Font.PLAIN, 15));
		tf_bplace_ar.setColumns(10);
		tf_bplace_ar.setBounds(309, 145, 148, 29);
		panel_7.add(tf_bplace_ar);

		JLabel label_16 = new JLabel("مكان الإزدياد بالعربية");
		label_16.setFont(new Font("Arial", Font.BOLD, 15));
		label_16.setBounds(94, 152, 134, 15);
		panel_7.add(label_16);

		JLabel lblDepartment = new JLabel("شعبة");
		lblDepartment.setFont(new Font("Arial", Font.BOLD, 15));
		lblDepartment.setBounds(12, 19, 44, 15);
		panel_7.add(lblDepartment);

		JPanel panel_3 = new JPanel( );
		panel_3.setBounds(12, 12, 571, 127);
		frame.getContentPane( ).add(panel_3);
		panel_3.setLayout(null);

		JLabel lblMrRchidAnas = new JLabel(String.format(
			"السيد %s, %s", empl.getName( ), empl.getFamilyName( )));
		lblMrRchidAnas.setFont(new Font("Arial", Font.BOLD, 15));
		lblMrRchidAnas.setBounds(308, 13, 251, 15);
		panel_3.add(lblMrRchidAnas);

		JLabel lblDiplomas = new JLabel("الملاحظات:");
		lblDiplomas.setFont(new Font("Arial", Font.BOLD, 15));
		lblDiplomas.setBounds(308, 39, 76, 15);
		panel_3.add(lblDiplomas);

		JPanel panel_4 = new JPanel( );
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(12, 12, 104, 103);
		panel_3.add(panel_4);
		panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		try {
			JLabel lbl_img = new JLabel(new ImageIcon(ImageIO.read(
				new File("data/imgs/" + empl.getCIN( ).toLowerCase( ) + ".png"))
							.getScaledInstance(
								(panel_4.getWidth( ) - 5),
								(panel_4.getHeight( ) - 5),
								Image.SCALE_SMOOTH)));
			lbl_img.setBounds(0, 0, 95, 95);
			lbl_img.setVisible(true);
			panel_4.add(lbl_img);
		} catch (IOException ex) {
			System.err.println(ex.getMessage( ));
			JLabel lblNewLabel = new JLabel("(Image)");
			lblNewLabel.setForeground(SystemColor.text);
			lblNewLabel.setBackground(SystemColor.controlLtHighlight);
			lblNewLabel.setBounds(17, 12, 70, 15);
			panel_4.add(lblNewLabel);
		}

		JLabel lblM = new JLabel(
			String.format("ب.ت.و.: %s", empl.getEmployeeReference( )));
		lblM.setFont(new Font("Arial", Font.BOLD, 15));
		lblM.setBounds(134, 13, 141, 15);
		panel_3.add(lblM);

		JLabel lblRef = new JLabel(String.format("ر.ت: %s", empl.getCIN( )));
		lblRef.setFont(new Font("Arial", Font.BOLD, 15));
		lblRef.setBounds(134, 39, 141, 15);
		panel_3.add(lblRef);

		JButton btnsave = new JButton("حفظ");
		btnsave.setFont(new Font("Arial", Font.BOLD, 15));
		btnsave.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				int dialogResult = JOptionPane.showConfirmDialog(null, "Sure?");
				if (dialogResult != JOptionPane.YES_OPTION) return;
				Employee nempl = new Employee(empl.getEmployeeReference( ));
				nempl.setDepartment(tf_d.getText( ));

				nempl.setNameArabic(tf_name_ar.getText( ));
				nempl.setFamilyNameArabic(tf_fname_ar.getText( ));
				nempl.setAddressArabic(tf_addr_ar.getText( ));
				nempl.setBirthPlaceArabic(tf_bplace_ar.getText( ));

				nempl.setName(tf_name.getText( ));
				nempl.setFamilyName(tf_fname.getText( ));
				nempl.setIsMoroccan(rd_isma_yes.isSelected( ));
				nempl.setBirthDay(DateUtil.parseDate(tf_bdate.getText( )));

				nempl.setBirthPlace(tf_bplace.getText( ));
				nempl.setAddress(tf_addr.getText( ));
				nempl.setPhone(tf_phone.getText( ));
				nempl.setIsMarried(rd_ismarried_yes.isSelected( ));

				nempl.setNumberOfchildren(
					Short.parseShort(spin_nchildren.getValue( ).toString( )));

				nempl.setPartnerName(tf_partner_name.getText( ));
				nempl.setPartnerJob(tf_partner_job.getText( ));

				nempl.setEmployeeReference(tf_ref.getText( ));
				nempl.setCIN(tf_cin.getText( ));
				nempl.setMission(tf_mission.getText( ));
				nempl.setJoinDate(DateUtil.parseDate(tf_date_join.getText( )));
				nempl.setHiringDate(
					DateUtil.parseDate(tf_date_hiring.getText( )));

				nempl.setPreviousJob(tf_job_prev.getText( ));
				nempl.setCurrentJob(tf_job_current.getText( ));
				nempl.setCadre(tf_cadre.getText( ));
				nempl.setFinancialStatus(tf_finance.getText( ));
				nempl.setReason(textField_1.getText( ));
				empl.update(nempl);
			}
		});
		btnsave.setBounds(505, 525, 80, 25);
		frame.getContentPane( ).add(btnsave);

		JButton button = new JButton("*");
		button.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				tbl_diplomas.setModel(Diploma.getDiplomasModel(empl));
				tbl_uplifts.setModel(Uplift.getUpliftModel(empl));
			}
		});
		button.setBounds(361, 525, 40, 25);
		frame.getContentPane( ).add(button);

		JButton btnnew = new JButton("جديد");
		btnnew.setFont(new Font("Arial", Font.BOLD, 15));
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
					nempl.setDepartment(tf_d.getText( ));

					nempl.setName(tf_name.getText( ));
					nempl.setFamilyName(tf_fname.getText( ));
					nempl.setIsMoroccan(rd_isma_yes.isSelected( ));
					nempl.setBirthDay(DateUtil.parseDate(tf_bdate.getText( )));

					nempl.setNameArabic(tf_name_ar.getText( ));
					nempl.setFamilyNameArabic(tf_fname_ar.getText( ));
					nempl.setAddressArabic(tf_addr_ar.getText( ));
					nempl.setBirthPlaceArabic(tf_bplace_ar.getText( ));

					nempl.setBirthPlace(tf_bplace.getText( ));
					nempl.setAddress(tf_addr.getText( ));
					nempl.setPhone(tf_phone.getText( ));
					nempl.setIsMarried(rd_ismarried_yes.isSelected( ));

					nempl.setNumberOfchildren(
						Short.parseShort(
							spin_nchildren.getValue( ).toString( )));
					nempl.setPartnerName(tf_partner_name.getText( ));
					nempl.setPartnerJob(tf_partner_job.getText( ));
					String strr = tf_ref.getText( ).compareTo("") == 0 ? "PAS"
									: tf_ref.getText( );
					nempl.setEmployeeReference(strr);
					nempl.setCIN(tf_cin.getText( ));
					nempl.setMission(tf_mission.getText( ));
					nempl.setJoinDate(
						DateUtil.parseDate(tf_date_join.getText( )));
					nempl.setHiringDate(
						DateUtil.parseDate(tf_date_hiring.getText( )));

					nempl.setPreviousJob(tf_job_prev.getText( ));
					nempl.setCurrentJob(tf_job_current.getText( ));
					nempl.setCadre(tf_cadre.getText( ));
					nempl.setFinancialStatus(tf_finance.getText( ));
					nempl.setReason(textField_1.getText( ));

					nempl.add( );

					// Uplift u = new Uplift( );
					// u.setEmployeeReference(nempl.getEmployeeReference( ));
					// u.setId(1);
					// u.setGrade((short) 9);
					// u.setRank((short) 1);
					// u.setIndice("127");
					// u.setDate(DateUtil.parseDate(new Date( ).toString( )));
					// u.add( );
					//
					// new UpliftCrud(nempl).getFrame( ).setVisible(true);

					// frame.dispose( );
				}
			}

			private void clearFields( ) {
				tf_addr.setText("");
				tf_bdate.setText(DateUtil.parseDate(new Date( )));
				tf_bplace.setText("");
				tf_cin.setText("");
				tf_d.setText("");
				tf_date_hiring.setText(DateUtil.parseDate(new Date( )));
				tf_date_join.setText(DateUtil.parseDate(new Date( )));
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

		JButton btndel = new JButton("حذف");
		btndel.setFont(new Font("Arial", Font.BOLD, 15));
		btndel.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				int dialogResult = JOptionPane.showConfirmDialog(null, "Sure?");
				if (dialogResult != JOptionPane.YES_OPTION) return;
				empl.remove( );
				frame.dispose( );

			}
		});
		btndel.setBounds(22, 525, 80, 25);
		frame.getContentPane( ).add(btndel);

	}
}
