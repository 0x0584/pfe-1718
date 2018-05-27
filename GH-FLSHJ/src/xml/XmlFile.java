package xml;

/*  Notes about this XmlFile
 *  ========================
 *  
 * 	deal with the XML storage of all the data and query the xml files
 *  this is specific to the classes in the model
 * */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import app.Files;
import app.Mention;
import app.Parser;
import app.SearchField;
import app.Type;
import model.Diploma;
import model.Employee;
import model.Employee.Cadre;
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

	public static ArrayList<Uplift> getUplifts(Employee empl) {
		ArrayList<Uplift> tmp = new ArrayList<Uplift>( );
		List<Element> dlist = getEmployee(empl.getReference( ))
						.getChild("administrative").getChildren("uplift");

		for (Element e : dlist) {
			Uplift u = new Uplift( );
			u.setRank(Short.parseShort(e.getChildTextTrim("echlon")));
			u.setDate(Parser.parseDate(e.getChildTextTrim("update")));
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

	public static void setEmployee(Employee empl, String ref) {
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
		empl.setBirthDay(Parser.parseDate(foo.getChildTextTrim("birth")));

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
		empl.setJoinDate(Parser.parseDate(bar.getChildTextTrim("jday")));
		empl.setHiringDate(Parser.parseDate(bar.getChildTextTrim("hday")));
		empl.setReason(bar.getChildTextTrim("reason"));
		empl.setPreviousJob(bar.getChildTextTrim("pjob"));
		empl.setCurrentJob(bar.getChildTextTrim("cjob"));
		empl.setCadre(Cadre.parseCadre(bar.getChildTextTrim("cadre")));
		empl.setFinancialStatus(bar.getChildTextTrim("fstatus"));
		empl.setUplifts(getUplifts(empl));
		empl.setDiplomas(getDiplomas(empl));

	}

	private static DefaultTableModel getModelColumns( ) {
		DefaultTableModel model = new DefaultTableModel( );

		model.addColumn("ر.ت.");
		model.addColumn("ب.ت.و.");
		model.addColumn("الإسم");
		model.addColumn("النسب");
		// model.addColumn("السلم");
		// model.addColumn("الرتبة");
		// model.addColumn("عدد الشواهد");

		return model;
	}

	public static DefaultTableModel getDefaultModel(Type t) {
		return (DefaultTableModel) getDefaultModel(null, null, t);
	}

	public static DefaultTableModel getDefaultModel(String text, SearchField sf,
		Type t) {
		DefaultTableModel model = getModelColumns( );
		Iterator<Element> ifoo; // temporary iterators
		Element foo; // temporary elements
		// String scale = null, echlon = null;
		boolean filter = !(sf == null || text.compareTo("") == 0);

		// loop over the employee
		ifoo = new XmlFile( ).getRoot( ).getChildren( ).iterator( );
		while (ifoo.hasNext( )) {
			foo = ifoo.next( );

			// skip unwanted elements
			if (filter && !foo.getChild(sf.getParent( ))
							.getChildTextTrim(sf.getXmlTag( )).toLowerCase( )
							.contains(text.toLowerCase( ))) {
				continue;
			}

			// skip employee based on filter
			if (t == Type.Normal && foo.getAttributeValue("department")
							.compareTo("nil") != 0) {
				continue; // this means that this is a professor
			} else if (t == Type.Prof && foo.getAttributeValue("department")
							.compareTo("nil") == 0) {
				continue; // this means that this is a normal one
			}

			// add row to model
			model.addRow(new String[] {
							foo.getAttributeValue("reference"),
							foo.getChild("administrative")
											.getChildTextTrim("cin"),
							foo.getChild("personal").getChildTextTrim("name"),
							foo.getChild("personal")
											.getChildTextTrim("familyname"),
							// scale, echlon, String.format(
							// "%d", foo.getChildren("diplomas").size( ))
			});
		}

		return model;
	}

	public static TableModel getDiplomasModel(Employee empl) {
		DefaultTableModel model = new DefaultTableModel( );

		for (String str : new String[] {
						"تاريخ الحصول عليها", "المؤسسة",

						"الميزة", "الشهادات",
		}) {
			model.addColumn(str);
		}

		for (Diploma d : empl.getDiplomas( )) {
			model.addRow(new String[] {
							d.getSession( ), d.getInstitue( ),
							d.getMention( ).toString( ), d.getTitle( )
			});
		}

		return model;
	}

	public static DefaultTableModel getUpliftModel(Employee empl) {
		DefaultTableModel model = new DefaultTableModel( );

		for (String str : new String[] {
						"التاريخ", "الرقم الإستدلالي",

						"الرتبة", "السلم",
		}) {
			model.addColumn(str);
		}

		for (Uplift u : empl.getUplifts( )) {
			model.addRow(new String[] {
							Parser.parseDate(u.getDate( )), u.getIndice( ),
							"" + u.getRank( ), "" + u.getGrade( )
			});
		}

		return model;
	}
}
