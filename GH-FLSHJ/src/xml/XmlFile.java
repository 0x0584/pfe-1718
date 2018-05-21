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

import javax.swing.table.DefaultTableModel;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import model.Diploma;
import model.Employee;
import model.Uplift;
import operation.Type;

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
			d.setTitle(e.getChildText(""));
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

	public void setEmployee(Employee empl, String ref) {
		Iterator<Element> i = new XmlFile( ).getRoot( ).getChildren( )
						.iterator( );
		Element e;
		String pname, pjob;
		short nchildren;

		while (i.hasNext( )) {
			if ((e = i.next( )).getAttributeValue("reference")
							.compareTo(ref) != 0) {
				continue;
			}
			empl.setName(e.getChild("personal").getChildText("name"));
			empl.setFamilyname(
				e.getChild("personal").getChildText("familyname"));
			// TODO: parse birth date
			empl.setBirthPlace(
				e.getChild("personal").getChildText("birthplace"));
			empl.setAddress(e.getChild("personal").getChildText("address"));
			empl.setPhone(e.getChild("personal").getChildText("phone"));
			empl.setIsMarried(
				e.getChild("personal").getChild("state")
								.getAttributeValue("married")
								.compareTo("t") == 0);
			
			// check if those are null
			try {
				pname = e.getChild("personal").getChild("partner")
								.getAttributeValue("name");
				pjob = e.getChild("personal").getChild("partner")
								.getAttributeValue("job");
				nchildren = Short.parseShort(
					e.getChild("personal").getChild("children")
									.getAttributeValue("number"));
			} catch (Exception ex) {
				pname = pjob = null;
				nchildren = 0;
			}

			empl.setPartnerName(pname);
			empl.setPartnerJob(pjob);
			empl.setNumberOfchildren(nchildren);
			//

			// empl.setName(e.getChild("personal").getChildText("name"));
			// empl.setName(e.getChild("personal").getChildText("name"));
			// empl.setName(e.getChild("personal").getChildText("name"));
			// empl.setName(e.getChild("personal").getChildText("name"));
			// empl.setName(e.getChild("personal").getChildText("name"));
		}
	}

	// TODO: fix column names
	public static DefaultTableModel getModelFromXml(Type t) {
		DefaultTableModel model = new DefaultTableModel( );
		String scale = null, echlon = null;
		Iterator<Element> ifoo, ibar; // temporary iterators
		Element foo, bar; // temporary elements

		// add columns to the model
		model.addColumn("ر.ت.");
		model.addColumn("ب.ت.و.");
		model.addColumn("الإسم");
		model.addColumn("النسب");
		model.addColumn("السلم");
		model.addColumn("الرتبة");
		model.addColumn("عدد الشواهد");

		// loop over the employee
		ifoo = new XmlFile( ).getRoot( ).getChildren( ).iterator( );
		while (ifoo.hasNext( )) {
			foo = ifoo.next( );
			// skip employee based on filter
			if (t == Type.Normal && foo.getAttributeValue("department")
							.compareTo("nil") != 0) {
				continue; // this means that this is a professor
			} else if (t == Type.Prof && foo.getAttributeValue("department")
							.compareTo("nil") == 0) {
				continue; // this means that this is a normal one
			}
			// get current scale and echlon
			ibar = foo.getChild("administrative").getChildren("uplift")
							.iterator( );
			while (ibar.hasNext( )) {
				bar = ibar.next( );
				if (bar.getAttributeValue("state").compareTo("current") == 0) {
					scale = bar.getChildText("scale");
					echlon = bar.getChildText("echlon");
					break;
				}
			}

			// add rows
			model.addRow(new String[] {
							foo.getAttributeValue("reference"),
							foo.getChild("administrative").getChildText("cin"),
							foo.getChild("personal").getChildText("name"),
							foo.getChild("personal").getChildText("familyname"),
							scale, echlon, String.format(
								"%d", foo.getChildren("diplomas").size( ))
			});
		}

		return model;
	}
}
