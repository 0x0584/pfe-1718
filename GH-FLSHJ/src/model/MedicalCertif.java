package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.jdom2.Attribute;
import org.jdom2.Element;

import app.Period;
import app.utils.DateUtil;
import app.utils.XmlElement;
import app.utils.XmlFile;

public class MedicalCertif extends XmlElement<MedicalCertif> {
	private int id, ndays;
	private Date from;
	private String period;

	public MedicalCertif() {

	}

	/**
	 * @param from
	 * @param ndays
	 * @param period
	 */
	public MedicalCertif(int id, Date from, int ndays, String period) {
		super( );
		this.from = from;
		this.ndays = ndays;
		this.period = period;
		this.id = id;
	}

	public int getId( ) {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFrom( ) {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public int getNumberOfDays( ) {
		return ndays;
	}

	public void setNumberOfDays(int ndays) {
		this.ndays = ndays;
	}

	public String getPeriod( ) {
		return period;
	}

	public void setPeriod(String p) {
		this.period = p;
	}

	@Override
	public String toString( ) {
		return "MedicalCertif [from=" + from + ", ndays=" + ndays
						+ ", semestre=" + period + "]";
	}

	@Override
	public boolean add( ) {
		try {
			Element empl = Employee.getEmployeeElement(empl_ref);
			Element med = new Element("medicalcertif");
			Element from = new Element("from");
			Element ndays = new Element("ndays");
			Element period = new Element("period");

			from.addContent(DateUtil.parseDate(this.from));
			ndays.addContent("" + this.ndays);
			period.addContent(this.period);

			Attribute id = new Attribute("id",
				"" + (MedicalCertif.getLastMedicalXmlId(empl) + 1));
			med.setAttribute(id);
			med.addContent(from);
			med.addContent(ndays);
			med.addContent(period);
			empl.addContent(med);

			System.err.println(empl.toString( ));
			XmlFile.writeXml(empl.getDocument( ));
			return true;
		} catch (Exception x) {
			System.err.println(x.getMessage( ));
			return false;
		}
	}

	@Override
	public boolean update(MedicalCertif updated) {
		try {
			Element e = Employee.getEmployeeElement(empl_ref);
			List<Element> list = e.getChildren("medicalcertif");

			for (Element el : list) {
				if (el.getAttributeValue("id").compareTo("" + id) == 0) {
					el.getChild("from")
									.setText(DateUtil.parseDate(updated.from));
					el.getChild("ndays").setText("" + updated.ndays);
					el.getChild("period").setText(updated.period);
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
			List<Element> list = e.getChildren("medicalcertif");

			for (Element el : list) {
				if (el.getAttributeValue("id").compareTo("" + this.id) == 0) {
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
		List<Element> dlist = empl.getChildren("medicalcertif");

		for (Element e : dlist) {
			if (e.getAttributeValue("id").compareTo("" + id) == 0) { return e; }
		}

		return null;
	}

	public static TableModel getMedicalModel(Employee empl) {
		DefaultTableModel model = new DefaultTableModel( );
		for (String col : new String[] {
						"من", "إلى", "عدد الأيام", "عطلة"
		}) {
			model.addColumn(col);
		}
		for (MedicalCertif c : getMedicalCertifs(
			Employee.getEmployeeElement(empl.getEmployeeReference( )))) {
			Date from = c.getFrom( );
			Date to = DateUtil.add(
				Period.ONE_DAY, c.getFrom( ), c.getNumberOfDays( ));
			model.addRow(new String[] {
							DateUtil.parseDate(from), DateUtil.parseDate(to),
							"" + c.getNumberOfDays( ), c.getPeriod( )
			});
		}
	
		return model;
	}

	public static ArrayList<MedicalCertif> getMedicalCertifs(Employee empl) {
		return getMedicalCertifs(empl.getElement( ));
	}

	public static ArrayList<MedicalCertif> getMedicalCertifs(Element empl) {
		List<Element> dlist = empl.getChildren("medicalcertif");
		ArrayList<MedicalCertif> tmp = new ArrayList<MedicalCertif>( );
		for (Element e : dlist) {
			MedicalCertif m = new MedicalCertif( );
			m.setEmployeeReference(empl.getAttributeValue("reference"));
			m.setId(Integer.parseInt(e.getAttributeValue("id")));
			m.setFrom(DateUtil.parseDate(e.getChildTextTrim("from")));
			m.setNumberOfDays(Integer.parseInt(e.getChildTextTrim("ndays")));
			m.setPeriod(e.getChildTextTrim("period"));

			tmp.add(m);
		}

		return tmp;
	}

	public static int getMedicalCertifXmlId(Element empl, int limit) {
		return XmlFile.getChildId("medicalcertif", empl, limit);
	}

	public static int getLastMedicalXmlId(Element empl) {
		return XmlFile.getChildId("medicalcertif", empl, Integer.MAX_VALUE);
	}

}
