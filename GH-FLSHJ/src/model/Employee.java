package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.jdom2.Document;
import org.jdom2.Element;

import app.utils.DateUtil;
import app.utils.XmlFile;

public class Employee extends Person {
	protected String cadre;
	protected String CIN, dep;
	// financial status
	protected String fstatus;
	protected String mission, reason, notes;
	// previous and current jobs
	protected String pjob, cjob;
	// hiring and joining date
	protected Date hdate, jdate;
	protected ArrayList<Uplift> uplifts;
	protected ArrayList<Diploma> diplomas;
	protected ArrayList<MedicalCertif> certifs;
	protected ArrayList<Repayment> repayments;
	private Uplift current;

	public Employee() {
		this("0112358");
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
		return repayments;
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

	public String getNotes( ) {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
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
		return current;
	}

	public void setCurrentUplift(Uplift current) {
		this.current = current;
	}

	@Override
	public boolean add( ) {
		try {
			Employee nempl = this;

			Element e = new Element("employee");
			e.setAttribute("department", nempl.getDepartment( ));
			e.setAttribute("reference", nempl.getEmployeeReference( ));

			Element notes = new Element("notes");
			notes.setText(nempl.getNotes( ));
			e.addContent(notes);

			Element personal = new Element("personal");
			Element name = new Element("name");
			name.setText(nempl.getName( ) + " ");

			Element fname = new Element("familyname");
			fname.setText(nempl.getFamilyName( ) + " ");

			Element bplace = new Element("brithplace");
			bplace.setText(nempl.getBirthPlace( ) + " ");

			Element bday = new Element("birth");
			bday.setText(DateUtil.parseDate(nempl.getBirthDay( )));

			Element addr = new Element("address");
			addr.setText(nempl.getAddress( ) + " ");

			Element phone = new Element("phone");
			phone.setText(nempl.getPhone( ) + " ");

			Element state = new Element("state");
			state.setAttribute("married", nempl.isMarried( ) ? "t" : "nil");

			Element isma = new Element("nationality");
			isma.setAttribute("ma", nempl.isMoroccan( ) ? "t" : "nil");

			Element partner = new Element("partner");
			partner.setAttribute("name", nempl.getPartnerName( ));
			partner.setAttribute("job", nempl.getPartnerJob( ));

			Element children = new Element("children");
			children.setAttribute("number", "" + nempl.getNumberOfChildren( ));

			personal.addContent(name);
			personal.addContent(fname);
			personal.addContent(bday);
			personal.addContent(bplace);
			personal.addContent(addr);
			personal.addContent(phone);
			personal.addContent(state);
			personal.addContent(isma);
			personal.addContent(partner);
			personal.addContent(children);
			e.addContent(personal);

			// administrative tag
			Element admini = new Element("administrative");

			Element cin = new Element("cin");
			cin.setText(nempl.getCIN( ) + " ");

			Element mission = new Element("mission");
			mission.setText(nempl.getMission( ) + " ");

			Element jday = new Element("jday");
			jday.setText(DateUtil.parseDate(nempl.getJoinDate( )));

			Element hday = new Element("hday");
			hday.setText(DateUtil.parseDate(nempl.getHiringDate( )));

			Element reason = new Element("reason");
			reason.setText(nempl.getReason( ) + " ");

			Element pjob = new Element("pjob");
			pjob.setText(nempl.getPreviousJob( ));

			Element cjob = new Element("cjob");
			cjob.setText(nempl.getCurrentJob( ));

			Element cadre = new Element("cadre");
			cadre.setText(nempl.getCadre( ).toString( ));

			Element fstate = new Element("fstatus");
			fstate.setText(nempl.getFinancialStatus( ));

			admini.addContent(cin);
			admini.addContent(mission);
			admini.addContent(jday);
			admini.addContent(hday);
			admini.addContent(reason);
			admini.addContent(pjob);
			admini.addContent(cjob);
			admini.addContent(cadre);
			admini.addContent(fstate);
			e.addContent(admini);

			XmlFile xml = new XmlFile( );
			Document doc = xml.getDoc( );
			Element root = xml.getRoot( );

			root.addContent(e);
			doc.setContent(root);

			XmlFile.writeXml(doc);
			return true;
		} catch (Exception x) {
			System.err.println(x.getMessage( ));
			return false;
		}
	}

	@Override
	public boolean update(Employee newempl) {
		try {
			Element empl = getElement( ), personal, admini;

			empl.getChild("notes").setText(newempl.getNotes( ));
			empl.getAttribute("department").setValue(newempl.getDepartment( ));
			empl.getAttribute("reference")
							.setValue(newempl.getEmployeeReference( ));

			// personal tag
			personal = empl.getChild("personal");

			XmlFile.updateOrCreate(personal, "name", newempl.getName( ));

			XmlFile.updateOrCreate(
				personal, "name_ar", newempl.getNameArabic( ));
			XmlFile.updateOrCreate(
				personal, "familyname_ar", newempl.getFamilyNameArabic( ));
			XmlFile.updateOrCreate(
				personal, "address_ar", newempl.getAddressArabic( ));
			XmlFile.updateOrCreate(
				personal, "birthplace_ar", newempl.getBirthPlaceArabic( ));

			XmlFile.updateOrCreate(
				personal, "familyname", newempl.getFamilyName( ));

			personal.getChild("nationality").getAttribute("ma")
							.setValue(newempl.isMoroccan( ) ? "t" : "nil");
			personal.getChild("birth").setText(
				DateUtil.parseDate(newempl.getBirthDay( )));

			XmlFile.updateOrCreate(personal, "address", newempl.getAddress( ));
			XmlFile.updateOrCreate(
				personal, "birthplace", newempl.getBirthPlace( ));
			XmlFile.updateOrCreate(personal, "phone", newempl.getPhone( ));
			personal.getChild("state").getAttribute("married")
							.setValue(newempl.isMarried( ) ? "t" : "nil");

			try {
				Element tmp = personal.getChild("partner");
				personal.getChild("children").getAttribute("number")
								.setValue("" + newempl.getNumberOfChildren( ));

				tmp.getAttribute("name").setValue(newempl.getPartnerName( ));
				tmp.getAttribute("job").setValue(newempl.getPartnerJob( ));
			} catch (Exception ex) {}

			// administrative tag
			admini = empl.getChild("administrative");

			XmlFile.updateOrCreate(admini, "cin", newempl.getCIN( ));
			XmlFile.updateOrCreate(admini, "mission", newempl.getMission( ));
			XmlFile.updateOrCreate(admini, "reason", newempl.getReason( ));
			XmlFile.updateOrCreate(admini, "pjob", newempl.getPreviousJob( ));
			XmlFile.updateOrCreate(admini, "cjob", newempl.getCurrentJob( ));
			XmlFile.updateOrCreate(
				admini, "fstatus", newempl.getFinancialStatus( ));

			admini.getChild("cin").setText(newempl.getCIN( ));
			admini.getChild("jday").setText(
				DateUtil.parseDate(newempl.getJoinDate( )));
			admini.getChild("hday").setText(
				DateUtil.parseDate(newempl.getHiringDate( )));
			admini.getChild("cadre").setText(newempl.getCadre( ).toString( ));

			System.err.println(empl);
			XmlFile.writeXml(empl.getDocument( ));
			return true;
		} catch (Exception x) {
			System.err.println(x.getMessage( ));
			return false;
		}
	}

	@Override
	public boolean remove( ) {
		try {
			Element e = getElement( );
			Document d = e.getDocument( );
			e.detach( );
			System.err.println(e.toString( ));
			XmlFile.writeXml(d);
			return true;
		} catch (Exception x) {
			System.err.println(x.getMessage( ));
			return false;
		}
	}

	@Override
	public Element getElement( ) {
		Iterator<Element> i;
		Element e;

		i = new XmlFile( ).getRoot( ).getChildren( ).iterator( );
		while (i.hasNext( )) {
			e = i.next( );
			if (e.getAttributeValue("reference").compareTo(empl_ref) != 0) {
				continue;
			} else return e;
		}

		return null;
	}

	public static Element getEmployeeElement(String ref) {
		Iterator<Element> i;
		Element e;

		i = new XmlFile( ).getRoot( ).getChildren( ).iterator( );
		while (i.hasNext( )) {
			e = i.next( );
			if (e.getAttributeValue("reference").compareTo(ref) != 0) {
				continue;
			} else return e;
		}

		return null;
	}

	public static Employee initEmployee(Employee empl, String ref) {
		Element e = Employee.getEmployeeElement(ref), foo, bar;
		// List<Element> dips, ups;

		empl.setNotes(e.getChildTextTrim("notes"));
		empl.setDepartment(e.getAttributeValue("department"));

		// personal tag
		foo = e.getChild("personal");

		empl.setNameArabic((String) foo.getChildTextTrim("name_ar"));
		empl.setFamilyNameArabic(
			(String) foo.getChildTextTrim("familyname_ar"));
		empl.setBirthPlaceArabic(
			(String) foo.getChildTextTrim("brithplace_ar"));
		empl.setAddressArabic((String) foo.getChildTextTrim("address_ar"));

		empl.setName(foo.getChildTextTrim("name"));
		empl.setFamilyName(foo.getChildTextTrim("familyname"));
		empl.setName(foo.getChildTextTrim("name"));
		empl.setFamilyName(foo.getChildTextTrim("familyname"));
		empl.setIsMoroccan(
			foo.getChild("nationality").getAttributeValue("ma")
							.compareTo("t") == 0);
		empl.setBirthDay(DateUtil.parseDate(foo.getChildTextTrim("birth")));

		empl.setBirthPlace(foo.getChildTextTrim("brithplace"));
		empl.setAddress(foo.getChildTextTrim("address"));

		empl.setPhone(foo.getChildTextTrim("phone"));
		empl.setIsMarried(
			foo.getChild("state").getAttributeValue("married")
							.compareTo("t") == 0);

		try {
			Element tmp = foo.getChild("partner");
			String str = foo.getChild("children").getAttributeValue("number");
			empl.setNumberOfchildren(Short.parseShort(str));
			empl.setPartnerName(tmp.getAttributeValue("name"));
			empl.setPartnerJob(tmp.getAttributeValue("job"));
		} catch (Exception ex) {
			empl.setPartnerName(null);
			empl.setPartnerJob(null);
			empl.setNumberOfchildren((short) 0);
		}

		// administrative tag
		bar = e.getChild("administrative");

		empl.setEmployeeReference(ref);
		empl.setCIN(bar.getChildTextTrim("cin"));
		empl.setMission(bar.getChildTextTrim("mission"));
		empl.setJoinDate(DateUtil.parseDate(bar.getChildTextTrim("jday")));
		empl.setHiringDate(DateUtil.parseDate(bar.getChildTextTrim("hday")));
		empl.setReason(bar.getChildTextTrim("reason"));
		empl.setPreviousJob(bar.getChildTextTrim("pjob"));
		empl.setCurrentJob(bar.getChildTextTrim("cjob"));
		empl.setCadre(bar.getChildTextTrim("cadre"));
		empl.setFinancialStatus(bar.getChildTextTrim("fstatus"));
		//
		empl.setUplifts(Uplift.getUplifts(e));
		empl.setCurrentUplift(Uplift.getCurrentUplift(e));
		empl.setDiplomas(Diploma.getDiplomas(e));
		empl.setMedicalCertifs(MedicalCertif.getMedicalCertifs(e));
		empl.setRepayments(Repayment.getRepayments(e));

		return empl;
	}

	public static int getLastEmployeeXmlReference( ) {
		int lastid = 0, limit = Integer.MAX_VALUE;

		for (Element el : new XmlFile( ).getRoot( ).getChildren( )) {
			lastid = Integer.parseInt(el.getAttributeValue("reference"));
			if (limit == 0) break;
			else limit--;
		}

		return lastid;
	}

	public static ArrayList<Employee> getAllEmployees( ) {
		Iterator<Element> i;
		Element e;
		ArrayList<Employee> list = new ArrayList<Employee>( );

		i = new XmlFile( ).getRoot( ).getChildren( ).iterator( );
		while (i.hasNext( )) {
			e = i.next( );
			list.add(new Employee(e.getAttributeValue("reference")));
		}

		return list;
	}

}
