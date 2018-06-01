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
import model.Uplift;

public class XmlFile {

	private String filepath;
	private File file;
	private Document doc;
	private XMLOutputter xmlout;
	private Element root;

	public XmlFile() {
		this(Files.HUMAIN_RESOURCES);
	}

	public XmlFile(Files file) {
		super( );
		this.xmlout = new XMLOutputter(Format.getPrettyFormat( ));
		setFilePath(file.getFilePath( ));
	}

	@Override
	public String toString( ) {
		return "XmlFile [filepath=" + filepath + ", file=" + file + ", doc="
						+ doc + ", xmlout=" + xmlout + ", rootNode=" + root
						+ "]";
	}

	public Element getRoot( ) {
		return root;
	}

	public String getFilePath( ) {
		return filepath;
	}

	public void setFilePath(String filepath) {
		this.file = new File(this.filepath = filepath);
		this.filepath = filepath;

		try {
			this.doc = new SAXBuilder( ).build(this.file);
			this.root = doc.getRootElement( );
		} catch (JDOMException | IOException e) {
			e.printStackTrace( );
			System.out.println(e.getMessage( ));
			// TODO: create a normal file if the original is not found
		}
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
		XMLOutputter xmlOutput = new XMLOutputter( );

		// display nice nice
		xmlOutput.setFormat(Format.getPrettyFormat( ));
		try {
			xmlOutput.output(
				doc, new FileWriter(Files.HUMAIN_RESOURCES.getFilePath( )));
			System.err.println("success " + f.getFilePath( ));
			return true;
		} catch (IOException e) {
			System.err.println(e.getMessage( ));
			return false;
		}
	}

	public static ArrayList<Uplift> getUplifts(Employee empl) {
		ArrayList<Uplift> tmp = new ArrayList<Uplift>( );
		List<Element> dlist = getEmployee(empl.getReference( ))
						.getChild("administrative").getChildren("uplift");

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

	public static ArrayList<Diploma> getDiplomas(Employee empl) {
		ArrayList<Diploma> tmp = new ArrayList<Diploma>( );
		List<Element> dlist = getEmployee(empl.getReference( ))
						.getChildren("diplomas");

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

	public static ArrayList<MedicalCertif> getMedicalCertifs(Employee empl) {
		List<Element> dlist = getEmployee(empl.getReference( ))
						.getChildren("medicalcertif");
		ArrayList<MedicalCertif> tmp = new ArrayList<MedicalCertif>( );
		for (Element e : dlist) {
			MedicalCertif m = new MedicalCertif( );
			m.setFrom(DateUtils.parseDate(e.getChildTextTrim("from")));
			m.setNumberOfDays(Integer.parseInt(e.getChildTextTrim("ndays")));
			m.setPeriod(e.getChildTextTrim("period"));

			tmp.add(m);
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
		empl.setUplifts(getUplifts(empl));
		empl.setDiplomas(getDiplomas(empl));
		empl.setMedicalCertifs(getMedicalCertifs(empl));
		
		return empl;
	}

	public static void updateEmployee(String ref, Employee newempl) {
		Element empl = getEmployee(ref), foo, bar;

		empl.getChild("notes").setText(newempl.getNotes( ));
		empl.getAttribute("department").setValue(newempl.getDepartment( ));
		empl.getAttribute("reference").setValue(newempl.getReference( ));

		// personal tag
		foo = empl.getChild("personal");

		foo.getChild("name").setText(newempl.getName( ));
		foo.getChild("familyname").setText(newempl.getFamilyname( ));
		foo.getChild("nationality").getAttribute("ma")
						.setValue(newempl.isMoroccan( ) ? "t" : "nil");
		foo.getChild("birth")
						.setText(DateUtils.parseDate(newempl.getBirthDay( )));

		foo.getChild("brithplace").setText(newempl.getBirthPlace( ));
		foo.getChild("address").setText(newempl.getAddress( ));
		foo.getChild("phone").setText(newempl.getPhone( ));
		foo.getChild("state").getAttribute("married")
						.setValue(newempl.isMarried( ) ? "t" : "nil");

		try {
			Element tmp = foo.getChild("partner");
			foo.getChild("children").getAttribute("number")
							.setValue("" + newempl.getNumberOfChildren( ));

			tmp.getAttribute("name").setValue(newempl.getPartnerName( ));
			tmp.getAttribute("job").setValue(newempl.getPartnerJob( ));
		} catch (Exception ex) {}

		// administrative tag
		bar = empl.getChild("administrative");

		bar.getChild("cin").setText(newempl.getCIN( ));
		bar.getChild("mission").setText(newempl.getMission( ));
		bar.getChild("jday")
						.setText(DateUtils.parseDate(newempl.getJoinDate( )));
		bar.getChild("hday")
						.setText(DateUtils.parseDate(newempl.getHiringDate( )));
		bar.getChild("reason").setText(newempl.getReason( ));
		bar.getChild("pjob").setText(newempl.getPreviousJob( ));
		bar.getChild("cjob").setText(newempl.getCurrentJob( ));
		bar.getChild("cadre").setText(newempl.getCadre( ).toString( ));
		bar.getChild("fstatus").setText(newempl.getFinancialStatus( ));

		// empl.setUplifts(getUplifts(empl));
		// empl.setDiplomas(getDiplomas(empl));

		XMLOutputter xmlOutput = new XMLOutputter( );

		// display nice nice
		xmlOutput.setFormat(Format.getPrettyFormat( ));
		try {
			xmlOutput.output(
				empl.getDocument( ),
				new FileWriter(Files.HUMAIN_RESOURCES.getFilePath( )));
			System.err.println(
				"success " + Files.HUMAIN_RESOURCES.getFilePath( ));
		} catch (IOException e) {
			System.err.println(e.getMessage( ));
		}

	}

	public static void getMedicalCertif(Employee empl, int id) {
		// TODO: finish this one
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
			"" + (employee.getCertifs( ).size( ) + 1));
		med.setAttribute(id);
		med.addContent(from);
		med.addContent(ndays);
		med.addContent(period);
		empl.addContent(med);

		System.err.println(empl.toString( ));
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

	public static void deleteMedicalCertif(Employee empl, MedicalCertif oldc) {
		Element e = getEmployee(empl.getReference( ));
		List<Element> list = e.getChildren("medicalcertif");

		for (Element el : list) {
			if (el.getAttributeValue("id").compareTo("" + oldc.getId( )) == 0) {
				el.detach( );
				break;
			}
		}
		System.err.println(e.toString( ));
		writeXml(e.getDocument( ));
	}
}
