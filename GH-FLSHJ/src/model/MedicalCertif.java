package model;

import java.util.Date;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Element;

import app.utils.DateUtils;
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
			Element empl = XmlFile.getEmployee(empl_ref);
			Element med = new Element("medicalcertif");
			Element from = new Element("from");
			Element ndays = new Element("ndays");
			Element period = new Element("period");

			from.addContent(DateUtils.parseDate(this.from));
			ndays.addContent("" + this.ndays);
			period.addContent(this.period);

			Attribute id = new Attribute("id",
				"" + (XmlFile.getLastMedicalId(empl) + 1));
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
			Element e = XmlFile.getEmployee(empl_ref);
			List<Element> list = e.getChildren("medicalcertif");

			for (Element el : list) {
				if (el.getAttributeValue("id").compareTo("" + id) == 0) {
					el.getChild("from")
									.setText(DateUtils.parseDate(updated.from));
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
			Element e = XmlFile.getEmployee(empl_ref);
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
	public Element getLast( ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Element getElement( ) {
		// TODO Auto-generated method stub
		return null;
	}

}
