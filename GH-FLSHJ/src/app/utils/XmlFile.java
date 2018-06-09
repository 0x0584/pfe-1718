package app.utils;

/*  Notes about this XmlFile
 *  ========================
 *  
 * 	deal with the XML storage of all the data and query the xml files
 *  this is specific to the classes in the model
 * */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import app.Cadre;
import app.Files;
import app.Mention;
import model.Diploma;
import model.Employee;
import model.MedicalCertif;
import model.Repayment;
import model.Uplift;

public class XmlFile {

	public static void addDiploma(Employee e, Diploma new_d) {
		Element empl = getEmployee(e.getReference( ));
		Element diploma = new Element("diplomas");
		Element title = new Element("diploma");
		Element inst = new Element("institute");
		Element sess = new Element("session");
		title.addContent(new_d.getTitle( ));
		title.setAttribute(
			new Attribute("mention", new_d.getMention( ).toString( )));
		inst.addContent(new_d.getInstitue( ));
		sess.addContent(new_d.getSession( ));

		Attribute id = new Attribute("id", "" + (getLastDiplomaId(e) + 1));
		diploma.setAttribute(id);
		diploma.addContent(title);
		diploma.addContent(inst);
		diploma.addContent(sess);
		empl.addContent(diploma);

		System.err.println(empl.toString( ));
		writeXml(empl.getDocument( ));

	}

	public static void addEmployee(Employee nempl) {
		Element e = new Element("employee");
		e.setAttribute("department", nempl.getDepartment( ));
		e.setAttribute("reference", nempl.getReference( ));

		Element notes = new Element("notes");
		notes.setText(nempl.getNotes( ));
		e.addContent(notes);

		Element personal = new Element("personal");
		Element name = new Element("name");
		name.setText(nempl.getName( ) + " ");

		Element fname = new Element("familyname");
		fname.setText(nempl.getFamilyname( ) + " ");

		Element bplace = new Element("brithplace");
		bplace.setText(nempl.getBirthPlace( ) + " ");

		Element bday = new Element("birth");
		bday.setText(DateUtils.parseDate(nempl.getBirthDay( )));

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
		jday.setText(DateUtils.parseDate(nempl.getJoinDate( )));

		Element hday = new Element("hday");
		hday.setText(DateUtils.parseDate(nempl.getHiringDate( )));

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
		Document doc = xml.doc;
		Element root = xml.root;

		root.addContent(e);
		doc.setContent(root);

		writeXml(doc);
	}

	public static void addMedicalCertif(Employee employee,
		MedicalCertif certif) {
		Element empl = getEmployee(employee.getReference( ));
		Element med = new Element("medicalcertif");
		Element from = new Element("from");
		Element ndays = new Element("ndays");
		Element period = new Element("period");

		from.addContent(DateUtils.parseDate(certif.getFrom( )));
		ndays.addContent("" + certif.getNumberOfDays( ));
		period.addContent(certif.getPeriod( ));

		Attribute id = new Attribute("id",
			"" + (getLastMedicalId(employee) + 1));
		med.setAttribute(id);
		med.addContent(from);
		med.addContent(ndays);
		med.addContent(period);
		empl.addContent(med);

		System.err.println(empl.toString( ));
		writeXml(empl.getDocument( ));
	}

	public static void addRepayment(Employee e, Repayment r) {
		Element empl = getEmployee(e.getReference( ));
		Element xml_repayed = new Element("repayment");
		Element ndays = new Element("ndays");
		Element repayed = new Element("repayed");
		Element period = new Element("period");

		repayed.addContent("" + r.getRepayedDays( ));
		ndays.addContent("" + r.getNumberOfDays( ));
		period.addContent(r.getPeriod( ));

		Attribute id = new Attribute("id", "" + (getLastRepaymentId(e) + 1));
		xml_repayed.setAttribute(id);
		xml_repayed.addContent(repayed);
		xml_repayed.addContent(ndays);
		xml_repayed.addContent(period);
		empl.addContent(xml_repayed);

		System.err.println(empl.toString( ));
		writeXml(empl.getDocument( ));
	}

	public static void addUplift(Employee e, Uplift new_r) {
		Element empl = getEmployee(e.getReference( ));
		Element xml_repayed = new Element("uplift");
		Element scale = new Element("scale");
		Element echlon = new Element("echlon");
		Element indice = new Element("indice");
		Element update = new Element("update");

		scale.addContent("" + new_r.getGrade( ));
		echlon.addContent("" + new_r.getRank( ));
		indice.addContent(new_r.getIndice( ));
		update.addContent(DateUtils.parseDate(new_r.getDate( )));

		Attribute id = new Attribute("id", "" + (getLastUpliftId(e) + 1));
		xml_repayed.setAttribute(id);
		xml_repayed.addContent(scale);
		xml_repayed.addContent(echlon);
		xml_repayed.addContent(indice);
		xml_repayed.addContent(update);
		empl.getChild("administrative").addContent(xml_repayed);

		System.err.println(empl.toString( ));
		writeXml(empl.getDocument( ));
	}

	public static void deleteDiploma(Employee empl, Diploma d) {
		Element e = getEmployee(empl.getReference( ));
		List<Element> list = e.getChildren("diplomas");

		for (Element el : list) {
			if (el.getAttributeValue("id").compareTo("" + d.getId( )) == 0) {
				el.detach( );
				break;
			}
		}

		System.err.println(e.toString( ));
		writeXml(e.getDocument( ));
	}

	public static void deleteEmployee(Employee empl) {
		Element e = getEmployee(empl.getReference( ));
		Document d = e.getDocument( );
		e.detach( );
		System.err.println(e.toString( ));
		writeXml(d);
	}

	public static void deleteMedicalCertif(Employee empl,
		MedicalCertif certif) {
		Element e = getEmployee(empl.getReference( ));
		List<Element> list = e.getChildren("medicalcertif");

		for (Element el : list) {
			if (el.getAttributeValue("id")
							.compareTo("" + certif.getId( )) == 0) {
				el.detach( );
				break;
			}
		}

		System.err.println(e.toString( ));
		writeXml(e.getDocument( ));
	}

	public static void deleteRepayment(Employee empl, Repayment r) {
		Element e = getEmployee(empl.getReference( ));
		List<Element> list = e.getChildren("repayment");

		for (Element el : list) {
			if (el.getAttributeValue("id").compareTo("" + r.getId( )) == 0) {
				el.detach( );
				break;
			}
		}

		System.err.println(e.toString( ));
		writeXml(e.getDocument( ));

	}

	public static void deleteUplift(Employee empl, Uplift r) {
		Element e = getEmployee(empl.getReference( ));
		List<Element> list = e.getChild("administrative").getChildren("uplift");

		for (Element el : list) {
			if (el.getAttributeValue("id").compareTo("" + r.getId( )) == 0) {
				el.detach( );
				break;
			}
		}

		System.err.println(e.toString( ));
		writeXml(e.getDocument( ));

	}

	public static int getChildId(String node, Employee empl, int limit) {
		int lastid = 0;
		Element e = getEmployee(empl.getReference( ));

		List<Element> list = e.getChildren(node);

		for (Element el : list) {
			lastid = Integer.parseInt(el.getAttributeValue("id"));
			if (limit == 0) {
				break;
			} else {
				--limit;
			}
		}

		return lastid;
	}

	public static ArrayList<Diploma> getDiplomas(Element empl) {
		ArrayList<Diploma> tmp = new ArrayList<Diploma>( );
		List<Element> dlist = empl.getChildren("diplomas");

		for (Element e : dlist) {
			Diploma d = new Diploma( );
			d.setInstitue(e.getChildTextTrim("institute"));
			d.setMention(
				Mention.parseMention(
					e.getChild("diploma").getAttributeValue("mention")));
			d.setSession(e.getChildTextTrim("session"));
			d.setTitle(e.getChildTextTrim("diploma"));
			tmp.add(d);
		}

		return tmp;
	}

	public static Element getEmployee(String ref) {
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

	public static int getLastDiplomaId(Employee empl) {
		return getChildId("diplomas", empl, Integer.MAX_VALUE);
	}

	public static int getlastEmployeeReference( ) {
		int lastid = 0;
		int limit = Integer.MAX_VALUE;

		for (Element el : new XmlFile( ).root.getChildren( )) {
			lastid = Integer.parseInt(el.getAttributeValue("reference"));
			if (limit == 0) break;
			else limit--;
		}

		return lastid;
	}

	public static int getLastMedicalId(Employee empl) {
		return getChildId("medicalcertif", empl, Integer.MAX_VALUE);
	}

	public static int getLastRepaymentId(Employee empl) {
		return getChildId("repayment", empl, Integer.MAX_VALUE);
	}

	public static int getLastUpliftId(Employee empl) {
		return getUpliftId(empl, Integer.MAX_VALUE);
	}

	public static int getMedicalCertifId(Employee empl, int limit) {
		return getChildId("medicalcertif", empl, limit);
	}

	public static ArrayList<MedicalCertif> getMedicalCertifs(Element empl) {
		List<Element> dlist = empl.getChildren("medicalcertif");
		ArrayList<MedicalCertif> tmp = new ArrayList<MedicalCertif>( );
		for (Element e : dlist) {
			MedicalCertif m = new MedicalCertif( );
			m.setId(Integer.parseInt(e.getAttributeValue("id")));
			m.setFrom(DateUtils.parseDate(e.getChildTextTrim("from")));
			m.setNumberOfDays(Integer.parseInt(e.getChildTextTrim("ndays")));
			m.setPeriod(e.getChildTextTrim("period"));

			tmp.add(m);
		}

		return tmp;
	}

	public static int getRepaymentId(Employee empl, int limit) {
		return getChildId("repayment", empl, limit);
	}

	private static ArrayList<Repayment> getRepayments(Element empl) {
		List<Element> dlist = empl.getChildren("repayment");
		ArrayList<Repayment> tmp = new ArrayList<Repayment>( );
		for (Element e : dlist) {
			Repayment r = new Repayment( );
			r.setId(Integer.parseInt(e.getAttributeValue("id")));
			r.setNumberOfDays(Integer.parseInt(e.getChildTextTrim("ndays")));
			r.setPeriod(e.getChildTextTrim("period"));
			r.setRepayedDays(Integer.parseInt(e.getChildTextTrim("repayed")));
			tmp.add(r);
		}

		return tmp;
	}

	public static int getUpliftId(Employee empl, int limit) {
		int lastid = 0;
		Element e = getEmployee(empl.getReference( ));

		List<Element> list = e.getChild("administrative").getChildren("uplift");

		for (Element el : list) {
			lastid = Integer.parseInt(el.getAttributeValue("id"));
			if (limit == 0) {
				break;
			} else {
				--limit;
			}
		}
		return lastid;
	}

	public static ArrayList<Uplift> getUplifts(Element empl) {
		ArrayList<Uplift> tmp = new ArrayList<Uplift>( );
		List<Element> dlist = empl.getChild("administrative")
						.getChildren("uplift");

		for (Element e : dlist) {
			Uplift u = new Uplift( );
			u.setRank(Short.parseShort(e.getChildTextTrim("echlon")));
			u.setDate(DateUtils.parseDate(e.getChildTextTrim("update")));
			u.setGrade(Short.parseShort(e.getChildTextTrim("scale")));
			u.setIndice(e.getChildTextTrim("indice"));
			tmp.add(u);
		}

		return tmp;
	}

	public static Employee initEmployee(Employee empl, String ref) {
		Element e = getEmployee(ref), foo, bar;
		// List<Element> dips, ups;

		empl.setNotes(e.getChildTextTrim("notes"));
		empl.setDepartment(e.getAttributeValue("department"));

		// personal tag
		foo = e.getChild("personal");

		empl.setName(foo.getChildTextTrim("name"));
		empl.setFamilyname(foo.getChildTextTrim("familyname"));
		empl.setIsMoroccan(
			foo.getChild("nationality").getAttributeValue("ma")
							.compareTo("t") == 0);
		empl.setBirthDay(DateUtils.parseDate(foo.getChildTextTrim("birth")));

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

		empl.setReference(ref);
		empl.setCIN(bar.getChildTextTrim("cin"));
		empl.setMission(bar.getChildTextTrim("mission"));
		empl.setJoinDate(DateUtils.parseDate(bar.getChildTextTrim("jday")));
		empl.setHiringDate(DateUtils.parseDate(bar.getChildTextTrim("hday")));
		empl.setReason(bar.getChildTextTrim("reason"));
		empl.setPreviousJob(bar.getChildTextTrim("pjob"));
		empl.setCurrentJob(bar.getChildTextTrim("cjob"));
		empl.setCadre(Cadre.parseCadre(bar.getChildTextTrim("cadre")));
		empl.setFinancialStatus(bar.getChildTextTrim("fstatus"));
		//
		empl.setUplifts(getUplifts(e));
		empl.setDiplomas(getDiplomas(e));
		empl.setMedicalCertifs(getMedicalCertifs(e));
		empl.setRepayments(getRepayments(e));

		return empl;
	}

	public static void updateDiploma(Employee empl, Diploma old_d,
		Diploma new_d) {
		Element e = getEmployee(empl.getReference( ));
		List<Element> list = e.getChildren("diplomas");

		for (Element el : list) {
			if (el.getAttributeValue("id")
							.compareTo("" + old_d.getId( )) == 0) {
				Element diploma = el.getChild("diploma");
				diploma.getAttribute("mention")
								.setValue(new_d.getMention( ).toString( ));
				diploma.setText(new_d.getTitle( ));
				el.getChild("institute").setText(new_d.getInstitue( ));
				el.getChild("session").setText(new_d.getSession( ));
				break;
			}
		}

		System.err.println(e.toString( ));
		writeXml(e.getDocument( ));

	}

	public static void updateEmployee(String ref, Employee newempl) {
		Element empl = getEmployee(ref), personal, admini;

		empl.getChild("notes").setText(newempl.getNotes( ));
		empl.getAttribute("department").setValue(newempl.getDepartment( ));
		empl.getAttribute("reference").setValue(newempl.getReference( ));

		// personal tag
		personal = empl.getChild("personal");

		updateOrCreate(personal, "name", newempl.getName( ));
		updateOrCreate(personal, "familyname", newempl.getFamilyname( ));

		personal.getChild("nationality").getAttribute("ma")
						.setValue(newempl.isMoroccan( ) ? "t" : "nil");
		personal.getChild("birth")
						.setText(DateUtils.parseDate(newempl.getBirthDay( )));

		updateOrCreate(personal, "address", newempl.getAddress( ));
		updateOrCreate(personal, "birthplace", newempl.getBirthPlace( ));
		updateOrCreate(personal, "phone", newempl.getPhone( ));
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

		updateOrCreate(admini, "cin", newempl.getCIN( ));
		updateOrCreate(admini, "mission", newempl.getMission( ));
		updateOrCreate(admini, "reason", newempl.getReason( ));
		updateOrCreate(admini, "pjob", newempl.getPreviousJob( ));
		updateOrCreate(admini, "cjob", newempl.getCurrentJob( ));
		updateOrCreate(admini, "fstatus", newempl.getFinancialStatus( ));

		admini.getChild("cin").setText(newempl.getCIN( ));
		admini.getChild("jday")
						.setText(DateUtils.parseDate(newempl.getJoinDate( )));
		admini.getChild("hday")
						.setText(DateUtils.parseDate(newempl.getHiringDate( )));
		admini.getChild("cadre").setText(newempl.getCadre( ).toString( ));

		writeXml(empl.getDocument( ));
	}

	public static void updateMedicalCertif(Employee empl, MedicalCertif oldc,
		MedicalCertif newc) {
		Element e = getEmployee(empl.getReference( ));
		List<Element> list = e.getChildren("medicalcertif");

		for (Element el : list) {
			if (el.getAttributeValue("id").compareTo("" + oldc.getId( )) == 0) {
				el.getChild("from")
								.setText(DateUtils.parseDate(newc.getFrom( )));
				el.getChild("ndays").setText("" + newc.getNumberOfDays( ));
				el.getChild("period").setText(newc.getPeriod( ));
				break;
			}
		}

		System.err.println(e.toString( ));
		writeXml(e.getDocument( ));
	}

	private static void updateOrCreate(Element el, String node, String value) {
		Element foo = el.getChild(node);

		if (foo == null) {
			Element bar = new Element(node);
			bar.addContent(value);
			el.addContent(bar);
			writeXml(el.getDocument( ));
		} else {
			foo.setText(value);
		}
	}

	public static void updateRepayment(Employee empl, Repayment old_r,
		Repayment new_r) {
		Element e = getEmployee(empl.getReference( ));
		List<Element> list = e.getChildren("repayment");

		for (Element el : list) {
			if (el.getAttributeValue("id")
							.compareTo("" + old_r.getId( )) == 0) {
				el.getChild("repayed").setText("" + new_r.getRepayedDays( ));
				el.getChild("ndays").setText("" + new_r.getNumberOfDays( ));
				el.getChild("period").setText(new_r.getPeriod( ));
				break;
			}
		}

		System.err.println(e.toString( ));
		writeXml(e.getDocument( ));

	}

	public static void updateUplift(Employee empl, Uplift old_r, Uplift new_r) {
		Element e = getEmployee(empl.getReference( ));
		List<Element> list = e.getChild("administrative").getChildren("uplift");

		for (Element el : list) {
			if (el.getAttributeValue("id")
							.compareTo("" + old_r.getId( )) == 0) {
				el.getChild("echlon").setText("" + new_r.getGrade( ));
				el.getChild("scale").setText("" + new_r.getRank( ));
				el.getChild("indice").setText("" + new_r.getIndice( ));
				el.getChild("update")
								.setText(DateUtils.parseDate(new_r.getDate( )));
				break;
			}
		}

		System.err.println(e.toString( ));
		writeXml(e.getDocument( ));

	}

	/**
	 * writes directly to human resources xml file
	 * 
	 * @param doc
	 *            xml document
	 * @return true if it succeeded
	 */
	public static boolean writeXml(Document doc) {
		return writeXml(doc, Files.HUMAIN_RESOURCES);
	}

	public static boolean writeXml(Document doc, Files f) {
		try {
			XMLOutputter xmlout = new XMLOutputter( );
			xmlout.setFormat(Format.getPrettyFormat( ));
			xmlout.output(doc, new FileWriter(f.getFilePath( )));
			System.err.println("success " + f.getFilePath( ));
			return true;
		} catch (IOException e) {
			System.err.println(e.getMessage( ));
			return false;
		}
	}

	private String filepath;

	private File file;

	private Document doc;

	private Element root;

	public XmlFile() {
		this(Files.HUMAIN_RESOURCES);
	}

	public XmlFile(Files file) {
		super( );
		setFilePath(file.getFilePath( ));
	}

	public String getFilePath( ) {
		return filepath;
	}

	public Element getRoot( ) {
		return root;
	}

	public void setFilePath(String filepath) {
		try {
			this.file = new File(this.filepath = filepath);
			if (file.exists( )) {
				this.doc = new SAXBuilder( ).build(this.file);
				this.root = doc.getRootElement( );
			} else {
				this.doc = new Document( );
				this.root = new Element("Employee");
			}
		} catch (JDOMException | IOException e) {
			System.out.println(e.getMessage( ));
			// XXX: create a normal file if the original is not found
		}
	}

}
