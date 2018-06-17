package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.jdom2.Attribute;
import org.jdom2.Element;

import app.Mention;
import app.utils.XmlElement;
import app.utils.XmlFile;

public class Diploma extends XmlElement<Diploma> {
	private int id;
	private String title, institue;
	private String session; // e.g. 2017-2018
	private Mention mention;

	public Diploma() {
		super( );

	}

	/**
	 * @param id
	 * @param title
	 * @param institue
	 * @param session
	 * @param mention
	 */
	public Diploma(	int id, String title, String institue, String session,
					Mention mention) {
		super( );
		this.setId(id);
		this.title = title;
		this.institue = institue;
		this.session = session;
		this.mention = mention;
	}

	@Override
	public String toString( ) {
		return "Diploma [title=" + title + ", institue=" + institue
						+ ", session=" + session + ", mention=" + mention + "]";
	}

	public int getId( ) {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle( ) {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getInstitue( ) {
		return institue;
	}

	public void setInstitue(String institue) {
		this.institue = institue;
	}

	public String getSession( ) {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public Mention getMention( ) {
		return mention;
	}

	public void setMention(Mention mention) {
		this.mention = mention;
	}

	@Override
	public boolean add( ) {
		try {
			Element empl = Employee.getEmployeeElement(empl_ref);
			Element diploma = new Element("diplomas");
			Element title = new Element("diploma");
			Element inst = new Element("institute");
			Element sess = new Element("session");
			title.addContent(title);
			title.setAttribute(new Attribute("mention", mention.toString( )));
			inst.addContent(institue);
			sess.addContent(session);

			Attribute id = new Attribute("id",
				"" + (Diploma.getLastDiplomaXmlId(empl) + 1));
			diploma.setAttribute(id);
			diploma.addContent(title);
			diploma.addContent(inst);
			diploma.addContent(sess);
			empl.addContent(diploma);

			System.err.println(empl.toString( ));
			XmlFile.writeXml(empl.getDocument( ));

			return true;
		} catch (Exception x) {
			System.err.println(x.getMessage( ));
			return false;
		}
	}

	@Override
	public boolean update(Diploma updated) {
		try {
			Element e = Employee.getEmployeeElement(empl_ref);
			List<Element> list = e.getChildren("diplomas");

			for (Element el : list) {
				if (el.getAttributeValue("id").compareTo("" + id) == 0) {
					Element diploma = el.getChild("diploma");
					diploma.getAttribute("mention").setValue(
						updated.getMention( ).toString( ));
					diploma.setText(updated.getTitle( ));
					el.getChild("institute").setText(updated.getInstitue( ));
					el.getChild("session").setText(updated.getSession( ));
					break;
				}
			}

			System.err.println(e.toString( ));
			XmlFile.writeXml(e.getDocument( ));
			return true;
		} catch (Exception x) {
			System.err.println(x.getMessage( ));
			return false;
		}
	}

	@Override
	public boolean remove( ) {
		try {
			Element e = Employee.getEmployeeElement(empl_ref);
			List<Element> list = e.getChildren("diplomas");

			for (Element el : list) {
				if (el.getAttributeValue("id").compareTo("" + id) == 0) {
					el.detach( );
					break;
				}
			}

			System.err.println(e.toString( ));
			XmlFile.writeXml(e.getDocument( ));
			return true;
		} catch (Exception x) {
			System.err.println(x.getMessage( ));
			return false;
		}
	}

	@Override
	public Element getElement( ) {
		Element empl = Employee.getEmployeeElement(empl_ref);
		List<Element> dlist = empl.getChildren("diplomas");

		for (Element e : dlist) {
			if (e.getAttributeValue("id").compareTo("" + id) == 0) return e;
		}

		return null;
	}

	public static TableModel getDiplomasModel(Employee empl) {
		DefaultTableModel model = new DefaultTableModel( );
	
		for (String str : new String[] {
						"تاريخ الحصول عليها", "المؤسسة",
	
						"الميزة", "الشهادات",
		}) {
			model.addColumn(str);
		}
	
		for (Diploma d : getDiplomas(
			Employee.getEmployeeElement(empl.getEmployeeReference( )))) {
			model.addRow(new String[] {
							d.getSession( ), d.getInstitue( ),
							d.getMention( ).toString( ), d.getTitle( )
			});
		}
	
		return model;
	}

	public static int getLastDiplomaXmlId(Element empl) {
		return XmlFile.getChildId("diplomas", empl, Integer.MAX_VALUE);
	}

	public static ArrayList<Diploma> getDiplomas(Employee empl) {
		return getDiplomas(empl.getElement( ));
	}

	public static ArrayList<Diploma> getDiplomas(Element empl) {
		ArrayList<Diploma> tmp = new ArrayList<Diploma>( );
		List<Element> dlist = empl.getChildren("diplomas");

		for (Element e : dlist) {
			Diploma d = new Diploma( );
			d.setEmployeeReference(empl.getAttributeValue("reference"));
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

	public static int getDiplomaXmlId(Element empl, int limit) {
		return XmlFile.getChildId("diplomas", empl, limit);
	}

}
