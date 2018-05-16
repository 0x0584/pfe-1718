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

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import model.Diploma;
import model.Employee;
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
}
