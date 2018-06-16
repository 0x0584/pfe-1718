package model;

import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Element;

import app.utils.XmlElement;
import app.utils.XmlFile;

public class Repayment extends XmlElement<Repayment> {
	private int id, ndays, repayed_days;
	private String period;

	public Repayment() {}

	/**
	 * @param id
	 * @param period
	 * @param ndays
	 * @param repayed_days
	 */
	public Repayment(int id, String period, int ndays, int repayed) {
		this.period = period;
		this.id = id;
		this.ndays = ndays;
		this.repayed_days = repayed;
	}

	public int getRest( ) {
		return ndays - repayed_days;
	}

	public String getPeriod( ) {
		return period;
	}

	public int getId( ) {
		return id;
	}

	public int getNumberOfDays( ) {
		return ndays;
	}

	public int getRepayedDays( ) {
		return repayed_days;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNumberOfDays(int ndays) {
		this.ndays = ndays;
	}

	public void setRepayedDays(int repayed_days) {
		this.repayed_days = repayed_days;
	}

	@Override
	public boolean add( ) {
		try {
			Element empl = XmlFile.getEmployee(empl_ref);
			Element xml_repayed = new Element("repayment");
			Element ndays = new Element("ndays");
			Element repayed = new Element("repayed");
			Element period = new Element("period");

			repayed.addContent("" + repayed_days);
			ndays.addContent("" + ndays);
			period.addContent(period);

			Attribute id = new Attribute("id",
				"" + (XmlFile.getLastRepaymentId(empl) + 1));
			xml_repayed.setAttribute(id);
			xml_repayed.addContent(repayed);
			xml_repayed.addContent(ndays);
			xml_repayed.addContent(period);
			empl.addContent(xml_repayed);

			System.err.println(empl.toString( ));
			XmlFile.writeXml(empl.getDocument( ));
			return true;
		} catch (Exception x) {
			System.err.println(x.getMessage( ));
			return false;
		}
	}

	@Override
	public boolean update(Repayment updated) {
		try {
			Element e = XmlFile.getEmployee(empl_ref);
			List<Element> list = e.getChildren("repayment");

			for (Element el : list) {
				if (el.getAttributeValue("id").compareTo("" + id) == 0) {
					el.getChild("repayed").setText("" + updated.repayed_days);
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
			List<Element> list = e.getChildren("repayment");

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
		// TODO Auto-generated method stub
		return null;
	}

}
