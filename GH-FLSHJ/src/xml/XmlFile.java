package xml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import model.AdministrativeStatus;
import model.Diploma;
import model.Employee;
import model.Uplift;

public class XmlFile {
	@SuppressWarnings("unused")
	private static String DEFAULT_FILENAMES[] = new String[] {
					"hr.xml"
	};
	private String filepath;
	private File file;
	private Document doc;
	private XMLOutputter xmlout;
	private Element rootNode;

	public XmlFile(String filepath) {
		super( );
		this.xmlout = new XMLOutputter(Format.getPrettyFormat( ));
		setFilePath(filepath);
	}

	@Override
	public String toString( ) {
		return "XmlFile [filepath=" + filepath + ", file=" + file + ", doc="
						+ doc + ", xmlout=" + xmlout + ", rootNode=" + rootNode
						+ "]";
	}

	public String getFilePath( ) {
		return filepath;
	}

	public void setFilePath(String filepath) {
		this.file = new File(this.filepath = filepath);
		this.filepath = filepath;

		try {
			this.doc = new SAXBuilder( ).build(this.file);
			this.rootNode = doc.getRootElement( );
		} catch (JDOMException | IOException e) {
			e.printStackTrace( );
			System.out.println(e.getMessage( ));
		}
	}


	public ArrayList<Diploma> getDiplomas( ) {
		ArrayList<Diploma> tmp = new ArrayList<Diploma>( );
		List<Element> list = rootNode.getChildren();
		// TODO: here
		return tmp;
	}

	public ArrayList<AdministrativeStatus> getAdministrativeStatuses( ) {
		ArrayList<AdministrativeStatus> tmp = new ArrayList<AdministrativeStatus>( );
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
