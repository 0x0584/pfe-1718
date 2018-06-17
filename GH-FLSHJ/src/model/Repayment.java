package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

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
			Element empl = Employee.getEmployeeElement(empl_ref);
			Element xml_repayed = new Element("repayment");
			Element ndays = new Element("ndays");
			Element repayed = new Element("repayed");
			Element period = new Element("period");

			repayed.addContent("" + repayed_days);
			ndays.addContent("" + ndays);
			period.addContent(period);

			Attribute id = new Attribute("id",
				"" + (Repayment.getLastRepaymentXmlId(empl) + 1));
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
			Element e = Employee.getEmployeeElement(empl_ref);
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
			Element e = Employee.getEmployeeElement(empl_ref);
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
		Element empl = Employee.getEmployeeElement(empl_ref);
		List<Element> dlist = empl.getChildren("repayment");

		for (Element e : dlist) {
			if (e.getAttributeValue("id").compareTo("" + id) == 0) { return e; }
		}

		return null;
	}

	public static TableModel getRepaymentModel(Employee empl) {
		DefaultTableModel model = new DefaultTableModel( );
		for (String col : new String[] {
						"السبب", "عدد الأيام", "المعوضة", "الباقي"
		}) {
			model.addColumn(col);
		}
		for (Repayment r : empl.getRepayments( )) {
			model.addRow(new String[] {
							r.getPeriod( ), "" + r.getNumberOfDays( ),
							"" + r.getRepayedDays( ), "" + r.getRest( )
			});
		}
	
		return model;
	}

	public static ArrayList<Repayment> getRepayments(Employee empl) {
		return getRepayments(empl.getElement( ));
	}
	
	public static ArrayList<Repayment> getRepayments(Element empl) {
		List<Element> dlist = empl.getChildren("repayment");
		ArrayList<Repayment> tmp = new ArrayList<Repayment>( );
		for (Element e : dlist) {
			Repayment r = new Repayment( );
			r.setEmployeeReference(empl.getAttributeValue("reference"));
			r.setId(Integer.parseInt(e.getAttributeValue("id")));
			r.setNumberOfDays(Integer.parseInt(e.getChildTextTrim("ndays")));
			r.setPeriod(e.getChildTextTrim("period"));
			r.setRepayedDays(Integer.parseInt(e.getChildTextTrim("repayed")));
			tmp.add(r);
		}

		return tmp;
	}

	public static int getRepaymentXmlId(Element empl, int limit) {
		return XmlFile.getChildId("repayment", empl, limit);
	}

	public static int getLastRepaymentXmlId(Element empl) {
		return XmlFile.getChildId("repayment", empl, Integer.MAX_VALUE);
	}

}
