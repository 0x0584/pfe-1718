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

	public static ArrayList<Employee> getAll( ) {
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

	public static int getChildId(String node, Element empl, int limit) {
		int lastid = 0;

		List<Element> list = empl.getChildren(node);

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

	private static Uplift getCurrentUplift(Element empl) {
		List<Element> dlist = empl.getChild("administrative")
						.getChildren("uplift");
		Uplift u = new Uplift( );

		for (Element e : dlist) {
			if (e.getAttributeValue("state").compareTo("current") == 0) {
				u.setEmployeeRefrence(empl.getAttributeValue("reference"));
				u.setRank(Short.parseShort(e.getChildTextTrim("echlon")));
				u.setDate(DateUtils.parseDate(e.getChildTextTrim("update")));
				u.setGrade(Short.parseShort(e.getChildTextTrim("scale")));
				u.setIndice(e.getChildTextTrim("indice"));
				break;
			}
		}

		return u;
	}

	public static Uplift getCurrentUplift(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}

	public static int getDiplomaId(Element empl, int limit) {
		return getChildId("diplomas", empl, limit);
	}

	public static ArrayList<Diploma> getDiplomas(Element empl) {
		ArrayList<Diploma> tmp = new ArrayList<Diploma>( );
		List<Element> dlist = empl.getChildren("diplomas");

		for (Element e : dlist) {
			Diploma d = new Diploma( );
			d.setEmployeeRefrence(empl.getAttributeValue("reference"));
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

	public static int getLastDiplomaId(Element empl) {
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

	public static int getLastMedicalId(Element empl) {
		return getChildId("medicalcertif", empl, Integer.MAX_VALUE);
	}

	public static int getLastRepaymentId(Element empl) {
		return getChildId("repayment", empl, Integer.MAX_VALUE);
	}

	public static int getLastUpliftId(Element empl) {
		return getUpliftId(empl, Integer.MAX_VALUE);
	}

	public static int getMedicalCertifId(Element empl, int limit) {
		return getChildId("medicalcertif", empl, limit);
	}

	public static ArrayList<MedicalCertif> getMedicalCertifs(Element empl) {
		List<Element> dlist = empl.getChildren("medicalcertif");
		ArrayList<MedicalCertif> tmp = new ArrayList<MedicalCertif>( );
		for (Element e : dlist) {
			MedicalCertif m = new MedicalCertif( );
			m.setEmployeeRefrence(empl.getAttributeValue("reference"));
			m.setId(Integer.parseInt(e.getAttributeValue("id")));
			m.setFrom(DateUtils.parseDate(e.getChildTextTrim("from")));
			m.setNumberOfDays(Integer.parseInt(e.getChildTextTrim("ndays")));
			m.setPeriod(e.getChildTextTrim("period"));

			tmp.add(m);
		}

		return tmp;
	}

	public static int getRepaymentId(Element empl, int limit) {
		return getChildId("repayment", empl, limit);
	}

	private static ArrayList<Repayment> getRepayments(Element empl) {
		List<Element> dlist = empl.getChildren("repayment");
		ArrayList<Repayment> tmp = new ArrayList<Repayment>( );
		for (Element e : dlist) {
			Repayment r = new Repayment( );
			r.setEmployeeRefrence(empl.getAttributeValue("reference"));
			r.setId(Integer.parseInt(e.getAttributeValue("id")));
			r.setNumberOfDays(Integer.parseInt(e.getChildTextTrim("ndays")));
			r.setPeriod(e.getChildTextTrim("period"));
			r.setRepayedDays(Integer.parseInt(e.getChildTextTrim("repayed")));
			tmp.add(r);
		}

		return tmp;
	}

	public static int getUpliftId(Element empl, int limit) {
		int lastid = 0;

		List<Element> list = empl.getChild("administrative")
						.getChildren("uplift");

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
			u.setEmployeeRefrence(empl.getAttributeValue("reference"));
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
		empl.setEmployeeRefrence(ref);
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
		empl.setCurrentUplift(getCurrentUplift(e));
		empl.setDiplomas(getDiplomas(e));
		empl.setMedicalCertifs(getMedicalCertifs(e));
		empl.setRepayments(getRepayments(e));

		return empl;
	}

	public static void updateOrCreate(Element el, String node, String value) {
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

	public Document getDoc( ) {
		return doc;
	}

	public String getFilePath( ) {
		return filepath;
	}

	public Element getRoot( ) {
		return root;
	}

	public void setDoc(Document doc) {
		this.doc = doc;
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

	public static void setOldUplift(Element empl) {
		List<Element> dlist = empl.getChild("administrative")
						.getChildren("uplift");

		for (Element e : dlist) {
			if (e.getAttributeValue("state").compareTo("current") == 0) {
				e.getAttribute("state").setValue("old");
				System.err.println(e.toString( ));
				writeXml(e.getDocument( ));
				break;
			}
		}
	}

}
