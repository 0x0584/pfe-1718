package xml;

/*  Notes about this XmlFile
 *  ========================
 *  
 * 	deal with the XML storage of all the data and query the xml files
 *  this is specific to the classes in the model
 * */

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

	public static ArrayList<Diploma> getDiplomas(Employee empl) {
		ArrayList<Diploma> tmp = new ArrayList<Diploma>( );
		// List<Element> list = rootNode.getChildren();
		// XmlSchema xs = XmlSchema.initSet(Diploma.class);

		Iterator<Element> i = new XmlFile( ).getRoot( ).getChildren( )
						.iterator( );

		while (i.hasNext( )) {
			Element e = i.next( );
			Diploma d = new Diploma( );
			d.setTitle(e.getChildTextTrim(""));
			// d.setInstitue(institue);
			// d.setMention(mention);
			// d.setSession(session);
			tmp.add(d);
		}

		return tmp;
	}

	public ArrayList<Employee> getEmployees( ) {
		ArrayList<Employee> tmp = new ArrayList<Employee>( );
		return tmp;
	}

	public ArrayList<Uplift> getUplift( ) {
		ArrayList<Uplift> tmp = new ArrayList<Uplift>( );
		return tmp;
	}

	private static Element getEmployee(String ref) {
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
		List<Element> dips, ups;

		empl.setNotes(e.getChildTextTrim("notes"));
		empl.setDepartment(e.getAttributeValue("department"));
		// personal tag
		foo = e.getChild("personal");

		empl.setName(foo.getChildTextTrim("name"));
		empl.setFamilyname(foo.getChildTextTrim("familyname"));
		empl.setIsMoroccan(
			foo.getChild("nationality").getAttributeValue("ma")
							.compareTo("t") == 0);
		try {
			empl.setBirthDay(
				new SimpleDateFormat("yyyy-mm-dd")
								.parse(foo.getChildTextTrim("birth")));
		} catch (ParseException e1) {
			empl.setBirthDay(new Date( ));
		}
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

		try {
			empl.setJoinDate(
				new SimpleDateFormat("yyyy-mm-dd")
								.parse(bar.getChildTextTrim("jday")));
		} catch (ParseException e1) {
			empl.setJoinDate(new Date( ));
		}

		try {
			empl.setHiringDate(
				new SimpleDateFormat("yyyy-mm-dd")
								.parse(bar.getChildTextTrim("hday")));
		} catch (ParseException e1) {
			empl.setHiringDate(new Date( ));
		}
		empl.setReason(bar.getChildTextTrim("reason"));
		empl.setPreviousJob(bar.getChildTextTrim("pjob"));
		empl.setCurrentJob(bar.getChildTextTrim("cjob"));
		empl.setCadre(Cadre.parseCadre(bar.getChildTextTrim("cadre")));
		empl.setFinancialStatus(bar.getChildTextTrim("fstatus"));

		// TODO: list of uplift tags
		ups = bar.getChildren("uplift");

		// TODO: list of diploma tags
		dips = e.getChildren("diplomas");
	}

	/**
	 * @param hiringdate
	 * @return
	 */
	public static ArrayList<Uplift> getUpliftsHistory(Date hiringdate) {
		ArrayList<Uplift> tmp = new ArrayList<Uplift>( );
		tmp.add(new Uplift("152/2018", new Date( ), (byte) 10, (byte) 4));
		return tmp;

		// TODO: this should be filled up automatically
		// using RANK and GRADE
	}

	private static DefaultTableModel getDefaultModel( ) {
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

	// TODO: fix column names
	public static DefaultTableModel getModel(Type t) {
		return (DefaultTableModel) getModel(null, null, t);
	}

	public static TableModel getModel(String text, SearchField sf, Type t) {
		DefaultTableModel model = getDefaultModel( );
		Iterator<Element> ifoo, ibar; // temporary iterators
		Element foo, bar; // temporary elements
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

			// get current scale and echlon
			// ibar = foo.getChild("administrative").getChildren("uplift")
			// .iterator( );
			// while (ibar.hasNext( )) {
			// bar = ibar.next( );
			// if (bar.getAttributeValue("state").compareTo("current") == 0) {
			// scale = bar.getChildTextTrim("scale");
			// echlon = bar.getChildTextTrim("echlon");
			// break;
			// }
			// }

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

}
