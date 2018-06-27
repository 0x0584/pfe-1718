package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import app.utils.DAO;
import app.utils.DateUtil;

public class Employee extends Person {
	protected String cadre;
	protected String CIN, dep;
	// financial status
	protected String fstatus;
	protected String mission, reason;
	// previous and current jobs
	protected String pjob, cjob;
	// hiring and joining date
	protected Date hdate, jdate;
	protected ArrayList<Uplift> uplifts;
	protected ArrayList<Diploma> diplomas;
	protected ArrayList<MedicalCertif> certifs;
	protected ArrayList<Repayment> repayments;
	
	public Employee() {
		this("111");
	}

	public Employee(String ref) {
		super( );
		Employee.initEmployee(this, ref);
	}

	public String getFinancialStatus( ) {
		return fstatus;
	}

	public ArrayList<MedicalCertif> getCertifs( ) {
		return certifs;
	}

	public ArrayList<Repayment> getRepayments( ) {
		ArrayList<Repayment> tmp = new ArrayList<Repayment>( );
		String query = "select * from repayment where refe = '" + empl_ref
						+ "' order by id desc";
		System.err.println(query);
		ResultSet r = new DAO( ).exec(query, false);

		try {
			while (r.next( )) {
				Repayment rr = new Repayment( );
				rr.setEmployeeReference(empl_ref);
				rr.setId(r.getInt("id"));
				rr.setNumberOfDays(r.getInt("ndays"));
				rr.setPeriod(r.getString("period"));
				rr.setRepayedDays(r.getInt("repayed"));
				tmp.add(rr);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage( ));
		}

		return tmp;
	}

	public void setRepayments(ArrayList<Repayment> repayments) {
		this.repayments = repayments;
	}

	public void setMedicalCertifs(ArrayList<MedicalCertif> certifs) {
		this.certifs = certifs;
	}

	public void setFinancialStatus(String fstatus) {
		this.fstatus = fstatus;
	}

	public String getReason( ) {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getPreviousJob( ) {
		return pjob;
	}

	public void setPreviousJob(String pjob) {
		this.pjob = pjob;
	}

	public String getCurrentJob( ) {
		return cjob;
	}

	public void setCurrentJob(String cjob) {
		this.cjob = cjob;
	}

	public void setHiringDate(Date hdate) {
		this.hdate = hdate;
	}

	public void setJoinDate(Date jdate) {
		this.jdate = jdate;
	}

	public void setMission(String mission) {
		this.mission = mission;
	}

	public ArrayList<Uplift> getUplifts( ) {
		return uplifts;
	}

	public void setUplifts(ArrayList<Uplift> uplifts) {
		this.uplifts = uplifts;
	}

	public ArrayList<Diploma> getDiplomas( ) {
		return diplomas;
	}

	public void setDiplomas(ArrayList<Diploma> diplomas) {
		this.diplomas = diplomas;
	}

	public String getMission( ) {
		return mission;
	}

	public String getShiftingReason( ) {
		return reason;
	}

	public Date getHiringDate( ) {
		return hdate;
	}

	public Date getJoinDate( ) {
		return jdate;
	}

	public String getCadre( ) {
		return cadre;
	}

	public void setCadre(String cadre) {
		this.cadre = cadre;
	}

	public String getCIN( ) {
		return CIN;
	}

	public void setCIN(String cin) {
		CIN = cin;
	}

	public String getDepartment( ) {
		return dep;
	}

	public void setDepartment(String dep) {
		this.dep = dep;
	}

	public boolean isProfessor( ) {
		return dep != null;
	}

	public Uplift getCurrentUplift( ) {
		String query = "select * from uplift where refe = '" + empl_ref
						+ "' order by id desc";
		System.err.println(query);
		ResultSet r = new DAO( ).exec(query, false);

		Uplift u = new Uplift( );

		try {
			while (r.next( )) {
				u.setEmployeeReference(empl_ref);
				u.setRank(r.getShort("echlon"));
				u.setDate(DateUtil.parseDate(r.getString("updatee")));
				u.setGrade(r.getShort("scalee"));
				u.setIndice(r.getString("indice"));
				break;
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage( ));
		}

		return u;
	}

	public void setCurrentUplift(Uplift current) {
	}

	@Override
	public void add( ) {
		String query = "insert into employee values('" + empl_ref + "','" + name
						+ "','" + fname + "','" + bplace + "','"
						+ DateUtil.parseDate(bday) + "','" + ismoroccan + "','"
						+ addr + "','" + phone + "','" + ismarried + "','"
						+ partnerName + "','" + partnerJob + "','" + nchildren
						+ "','" + bplace_ar + "','" + name_ar + "','" + fname_ar
						+ "','" + addr_ar + "')";
		System.err.println(query);
		new DAO( ).exec(query, true);

		query = "insert into admini values('" + empl_ref + "','" + cadre + "','"
						+ fstatus + "','" + CIN + "','" + mission + "','"
						+ DateUtil.parseDate(hdate) + "','"
						+ DateUtil.parseDate(jdate) + "','" + reason + "','"
						+ pjob + "','" + cjob + "','" + dep + "')";
		System.err.println(query);
		new DAO( ).exec(query, true);

	}

	@Override
	public void update(Employee e) {
		String query = "update employee set dep = '" + e.dep + "',fisrt_name ='"
						+ e.name + "',last_name='" + e.fname + "',bplace='"
						+ e.bplace + "',bdate='" + DateUtil.parseDate(e.bday)
						+ "',nationa='" + e.ismoroccan + "',address='" + e.addr
						+ "',phone='" + e.phone + "',ismarried='" + e.ismarried
						+ "',partner_name='" + e.partnerName + "',partner_job='"
						+ e.partnerJob + "',children='" + e.nchildren
						+ "',bplace_ar='" + e.bplace_ar + "',fisrt_name_ar='"
						+ e.name_ar + "',last_name_ar='" + e.fname_ar
						+ "',address_ar='" + e.addr_ar + "' where refe = '"
						+ empl_ref + "'";
		System.err.println(query);
		new DAO( ).exec(query, true);

		query = "update admini set cadre = '" + e.cadre + "',fstatus='"
						+ fstatus + "',cin='" + e.CIN + "',mission='"
						+ e.mission + "',hday='" + DateUtil.parseDate(e.hdate)
						+ "',jday='" + DateUtil.parseDate(e.jdate)
						+ "',reason='" + reason + "',pjob='" + e.pjob
						+ "',cjob='" + e.cjob + "' where refe = '" + empl_ref
						+ "'";
		System.err.println(query);
		new DAO( ).exec(query, true);
	}

	@Override
	public void remove( ) {
		String query = "delete from employee where refe='" + empl_ref + "'";
		System.err.println(query);
		new DAO( ).exec(query, true);

		query = "delete from admini where refe='" + empl_ref + "'";
		System.err.println(query);
		new DAO( ).exec(query, true);
	}

	public static Employee initEmployee(Employee empl, String ref) {

		String query = "select * from employee e, admini a"
						+ " where e.refe = a.refe and e.refe = '" + ref + "'";
		System.err.println(query);
		ResultSet r = new DAO( ).exec(query, false);

		try {
			while (r.next( )) {
				empl.setDepartment(r.getString("dep"));
				empl.setNameArabic(r.getString("first_name_ar"));
				empl.setFamilyNameArabic(r.getString("last_name_ar"));
				empl.setBirthPlaceArabic(r.getString("bplace_ar"));
				empl.setAddressArabic(r.getString("address_ar"));

				empl.setName(r.getString("fisrt_name"));
				empl.setFamilyName(r.getString("last_name_ar"));
				empl.setIsMoroccan(r.getBoolean("nationa"));
				empl.setBirthDay(DateUtil.parseDate(r.getString("bdate")));

				empl.setBirthPlace(r.getString("bplace"));
				empl.setAddress(r.getString("address"));

				empl.setPhone(r.getString("phone"));
				empl.setIsMarried(r.getBoolean("ismarried"));

				empl.setNumberOfchildren(r.getShort("children"));
				empl.setPartnerName(r.getString("partner_name"));
				empl.setPartnerJob(r.getString("partner_job"));

				empl.setEmployeeReference(ref);
				empl.setCIN(r.getString("cin"));
				empl.setMission(r.getString("mission"));
				empl.setJoinDate(DateUtil.parseDate(r.getString("jday")));
				empl.setHiringDate(DateUtil.parseDate(r.getString("hday")));
				empl.setReason(r.getString("reason"));
				empl.setPreviousJob(r.getString("pjob"));
				empl.setCurrentJob(r.getString("cjob"));
				empl.setCadre(r.getString("cadre"));
				empl.setFinancialStatus(r.getString("fstatus"));

				empl.setUplifts(Uplift.getUplifts(ref));
				empl.setCurrentUplift(Uplift.getCurrentUplift(ref));
				empl.setDiplomas(Diploma.getDiplomas(ref));
				empl.setMedicalCertifs(MedicalCertif.getMedicalCertifs(ref));
				empl.setRepayments(Repayment.getRepayments(ref));

			}
		} catch (SQLException e) {
			System.err.println(e.getMessage( ));
		}

		return empl;
	}

	public static ArrayList<Employee> getAllEmployees( ) {
		ArrayList<Employee> list = new ArrayList<Employee>( );
		ArrayList<String> str = new ArrayList<String>( );

		String query = "select refe from employee ";
		System.err.println(query);

		ResultSet r = new DAO( ).exec(query, false);
		try {
			while (r.next( )) {
				str.add(r.getString("refe"));
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage( ));
		}

		for (String ref : str) {
			list.add(Employee.initEmployee(new Employee( ), ref));
		}

		return list;
	}

}
