package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.jdom2.Attribute;
import org.jdom2.Element;

import app.InNext;
import app.Period;
import app.utils.DateUtil;
import app.utils.XmlElement;
import app.utils.XmlFile;

public class Uplift extends XmlElement<Uplift> {
	private int id;
	private String indice;
	private Date date;
	private short grade;
	private short rank;
	// private boolean byexam;

	public Uplift() {
		this.grade = 9;
		this.rank = 1;
		this.date = new Date( );
		this.indice = "none";
		this.id = 1;
	}

	/**
	 * @param indice
	 * @param date
	 * @param grade
	 * @param rank
	 */
	public Uplift(int id, String indice, Date date, short grade, short rank) {
		super( );
		this.id = id;
		this.indice = indice;
		this.date = date;
		this.grade = grade;
		this.rank = rank;
	}

	@Override
	public String toString( ) {
		return "Uplift [grade=" + grade + ", rank=" + rank + ", indice="
						+ indice + ", date=" + date + "]";
	}

	public int getId( ) {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public short getGrade( ) {
		return grade;
	}

	public void setGrade(short grade) {
		this.grade = grade;
	}

	public short getRank( ) {
		return rank;
	}

	public void setRank(short rank) {
		this.rank = rank;
	}

	public String getIndice( ) {
		return indice;
	}

	public void setIndice(String indice) {
		this.indice = indice;
	}

	public Date getDate( ) {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * this function tracks the uplifts once in a start up checks if some
	 * employees would get an uplift in the next period
	 *
	 * @param period
	 *            the period to track
	 * @return a JTable model
	 */
	public static TableModel getUpcomingUplifts(Period period) {
		Date todate = Period.getDate(period);
		DefaultTableModel model = new DefaultTableModel( );
		String[] cols = new String[] {
						"ر. التأجير", "ب.ت.و.", "الإسم الكامل",

						"الأيام المتبقية", "تاريخ الترقية", "السلم", "الرتبة"

		};

		for (String col_name : cols) {
			model.addColumn(col_name);
		}

		for (Employee e : Employee.getAllEmployees( )) {
			Uplift next = e.getCurrentUplift( ).next( );
			Date nextdate = next.getDate( ), today = new Date( );
			if (DateUtil.diff(today, nextdate) < 0) continue;

			if (nextdate.before(todate)) {
				String fullname = e.getName( ) + " "
								+ e.getFamilyName( ).toUpperCase( );
				model.addRow(new String[] {
								e.getEmployeeReference( ), e.getCIN( ),
								fullname,
								"" + DateUtil.diffDays(today, nextdate),
								DateUtil.parseDate(nextdate),
								"" + next.getGrade( ), "" + next.getRank( )
				});
			}
		}

		return model;
	}

	/**
	 * TODO: fix this shit generate the next Uplift in order
	 * 
	 * @return the next uplift
	 */
	public Uplift next( ) {
		Uplift u = new Uplift( );
		int n_years = 0;
		short grade = this.grade, rank = this.rank;

		/* determine the # of years to add based on the rank */
		if (rank == 1 || rank == 2 || rank == 3) {
			n_years = 1;
		} else if (rank == 4 || rank == 5 || rank == 6) {
			n_years = 2;
		} else if (rank == 7 || rank == 8 || rank == 9) {
			n_years = 3;
		} else if (rank == 10) {
			n_years = 4;
		} else if (rank == 11) {
			n_years = 3;
		}

		Date nextdate = DateUtil.add(Period.ONE_YEAR, date, n_years);

		if (grade == 12) {
			rank = 1;
		} else if (rank < 11) {
			rank++;
		} else {
			rank = 1;
			if (grade != 12) {
				grade++;
			}
		}

		u.setId(-1);
		u.setDate(nextdate);
		u.setGrade(grade);
		u.setRank(rank);
		u.setIndice("");

		return u;
	}

	public Uplift previous( ) {
		return null;
	}

	@Override
	public boolean add( ) {
		try {
			Element empl = Employee.getEmployeeElement(empl_ref);
			Uplift.setOldUplift(empl);

			Element xml_repayed = new Element("uplift");
			Element scale = new Element("scale");
			Element echlon = new Element("echlon");
			Element indice = new Element("indice");
			Element update = new Element("update");

			scale.addContent("" + this.grade);
			echlon.addContent("" + this.rank);
			indice.addContent(this.indice);
			update.addContent(DateUtil.parseDate(date));

			Attribute id = new Attribute("id",
				"" + (Uplift.getLastUpliftXmlId(empl) + 1));
			Attribute state = new Attribute("state", "current");

			xml_repayed.setAttribute(id);
			xml_repayed.setAttribute(state);
			xml_repayed.addContent(scale);
			xml_repayed.addContent(echlon);
			xml_repayed.addContent(indice);
			xml_repayed.addContent(update);
			empl.getChild("administrative").addContent(xml_repayed);

			System.err.println(empl.toString( ));
			XmlFile.writeXml(empl.getDocument( ));
			return true;
		} catch (Exception x) {
			System.err.println(x.getMessage( ));
			return false;
		}
	}

	@Override
	public boolean update(Uplift updated) {
		try {
			Element e = Employee.getEmployeeElement(empl_ref);
			List<Element> list = e.getChild("administrative")
							.getChildren("uplift");

			for (Element el : list) {
				if (el.getAttributeValue("id").compareTo("" + id) == 0) {
					el.getChild("echlon").setText("" + updated.getGrade( ));
					el.getChild("scale").setText("" + updated.getRank( ));
					el.getChild("indice").setText("" + updated.getIndice( ));
					el.getChild("update").setText(
						DateUtil.parseDate(updated.getDate( )));
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
			List<Element> list = e.getChild("administrative")
							.getChildren("uplift");

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
		return null;
	}

	/**
	 * List of all the pending uplifts to confirm
	 * 
	 * @return a Table model
	 */
	public static TableModel getPendingUplifts( ) {
		DefaultTableModel model = new DefaultTableModel( );
		String[] cols = new String[] {
						"ر. التأجير", "ب.ت.و.", "الإسم الكامل",

						"السلم", "الرتبة"

		};

		for (String col_name : cols) {
			model.addColumn(col_name);
		}

		for (Employee e : Employee.getAllEmployees( )) {
			Uplift next = e.getCurrentUplift( ).next( );
			Date nextdate = next.getDate( );

			if (nextdate.before(Period.getDate(Period.TODAY))) {
				String fullname = e.getName( ) + " "
								+ e.getFamilyName( ).toUpperCase( );
				model.addRow(new String[] {
								e.getEmployeeReference( ), e.getCIN( ),
								fullname, "" + next.getGrade( ),
								"" + next.getRank( )
				});
			}
		}

		return model;
	}

	public static DefaultTableModel getUpliftModel(Employee empl) {
		DefaultTableModel model = new DefaultTableModel( );

		for (String str : new String[] {
						"التاريخ", "الرقم الإستدلالي",

						"الرتبة", "السلم",
		}) {
			model.addColumn(str);
		}

		for (Uplift u : getUplifts(
			Employee.getEmployeeElement(empl.getEmployeeReference( )))) {
			model.addRow(new String[] {
							DateUtil.parseDate(u.getDate( )), u.getIndice( ),
							"" + u.getRank( ), "" + u.getGrade( )
			});
		}

		return model;
	}

	public static void setOldUplift(Element empl) {
		List<Element> dlist = empl.getChild("administrative")
						.getChildren("uplift");

		for (Element e : dlist) {
			if (e.getAttributeValue("state").compareTo("current") == 0) {
				e.getAttribute("state").setValue("old");
				System.err.println(e.toString( ));
				XmlFile.writeXml(e.getDocument( ));
				break;
			}
		}
	}

	public static ArrayList<Uplift> getUplifts(Employee empl) {
		return getUplifts(empl.getElement( ));
	}

	public static ArrayList<Uplift> getUplifts(Element empl) {
		ArrayList<Uplift> tmp = new ArrayList<Uplift>( );
		List<Element> dlist = empl.getChild("administrative")
						.getChildren("uplift");

		for (Element e : dlist) {
			Uplift u = new Uplift( );
			u.setEmployeeReference(empl.getAttributeValue("reference"));
			u.setRank(Short.parseShort(e.getChildTextTrim("echlon")));
			u.setDate(DateUtil.parseDate(e.getChildTextTrim("update")));
			u.setGrade(Short.parseShort(e.getChildTextTrim("scale")));
			u.setIndice(e.getChildTextTrim("indice"));
			tmp.add(u);
		}

		return tmp;
	}

	public static int getUpliftXmlId(Element empl, int limit) {
		int lastid = 0;

		List<Element> list = empl.getChild("administrative")
						.getChildren("uplift");

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

	public static int getLastUpliftXmlId(Element empl) {
		return Uplift.getUpliftXmlId(empl, Integer.MAX_VALUE);
	}

	public static Uplift getCurrentUplift(Employee empl) {
		return getCurrentUplift(empl.getElement( ));
	}

	public static Uplift getCurrentUplift(Element empl) {
		List<Element> dlist = empl.getChild("administrative")
						.getChildren("uplift");
		Uplift u = new Uplift( );

		for (Element e : dlist) {
			if (e.getAttributeValue("state").compareTo("current") == 0) {
				u.setEmployeeReference(empl.getAttributeValue("reference"));
				u.setRank(Short.parseShort(e.getChildTextTrim("echlon")));
				u.setDate(DateUtil.parseDate(e.getChildTextTrim("update")));
				u.setGrade(Short.parseShort(e.getChildTextTrim("scale")));
				u.setIndice(e.getChildTextTrim("indice"));
				break;
			}
		}

		return u;
	}

	@SuppressWarnings("deprecation")
	public static TableModel getInNextModel(int i) {
		Date todate = InNext.parse(i);
		DefaultTableModel model = new DefaultTableModel( );
		String[] cols = new String[] {
						"ر. التأجير", "ب.ت.و.", "الإسم الكامل",

						"تاريخ الترقية", "السلم", "الرتبة"

		};

		for (String col_name : cols) {
			model.addColumn(col_name);
		}

		for (Employee e : Employee.getAllEmployees( )) {
			Uplift next = e.getCurrentUplift( ).next( );
			Date nextdate = next.getDate( );

			if (nextdate.getYear( ) == todate.getYear( )) {
				String fullname = e.getName( ) + " "
								+ e.getFamilyName( ).toUpperCase( );
				model.addRow(new String[] {
								e.getEmployeeReference( ), e.getCIN( ),
								fullname, DateUtil.parseDate(nextdate),
								"" + next.getGrade( ), "" + next.getRank( )
				});
			}
		}

		return model;
	}

}
