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
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import app.Files;

public class XmlFile {

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
		}
	}

}
