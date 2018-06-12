package model;

import java.util.Date;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.jdom2.Attribute;
import org.jdom2.Element;

import app.Period;
import app.utils.DateUtils;
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
	 */
	public static TableModel trackUplifts(Period p) {
		Date todate = Period.getDate(p);
		DefaultTableModel model = new DefaultTableModel( );
		String[] cols = new String[] {
						"reference", "cin", "full name",

						"uplift in", "exact date", "grade", "rank"

		};

		for (String col_name : cols) {
			model.addColumn(col_name);
		}

		for (Employee e : XmlFile.getAll( )) {
			/*
			 * TODO: to retrieve the Uplift with state="current"
			 */
			Uplift next = e.getCurrentUplift( ).next( );
			Date nextdate = next.getDate( ), today = new Date( );

			if (nextdate.before(todate)) {
				String fullname = e.getName( ) + " "
								+ e.getFamilyname( ).toUpperCase( );
				model.addRow(new String[] {
								e.getCIN( ), e.getReference( ), fullname,
								"" + DateUtils.DateDiff(today, nextdate),
								DateUtils.parseDate(nextdate),
								"" + next.getGrade( ), "" + next.getRank( )
				});
			}
		}

		return model;
	}

	public Uplift next( ) {
		Uplift u = new Uplift( );
		int n_years = 0;
		short grade = this.grade, rank = this.rank;

		/* determine the # of years to add based on the rank */
		if (rank >= 1 && rank <= 4) {
			n_years = 1;
		} else if (rank == 5 || rank == 6) {
			n_years = 2;
		} else if (rank == 7 || rank == 8 || rank == 9) {
			n_years = 3;
		} else if (rank == 10) {
			n_years = 4;
		} else if (rank == 11) {
			n_years = 3;
		}

		Date nextdate = DateUtils.add(Period.ONE_YEAR, date, n_years);

		if (rank < 11) {
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add( ) {
		try {
			Element empl = XmlFile.getEmployee(empl_ref);
			Element xml_repayed = new Element("uplift");
			Element scale = new Element("scale");
			Element echlon = new Element("echlon");
			Element indice = new Element("indice");
			Element update = new Element("update");

			scale.addContent("" + grade);
			echlon.addContent("" + rank);
			indice.addContent(indice);
			update.addContent(DateUtils.parseDate(date));

			Attribute id = new Attribute("id",
				"" + (XmlFile.getLastUpliftId(empl) + 1));
			xml_repayed.setAttribute(id);
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
			Element e = XmlFile.getEmployee(empl_ref);
			List<Element> list = e.getChild("administrative")
							.getChildren("uplift");

			for (Element el : list) {
				if (el.getAttributeValue("id").compareTo("" + id) == 0) {
					el.getChild("echlon").setText("" + updated.getGrade( ));
					el.getChild("scale").setText("" + updated.getRank( ));
					el.getChild("indice").setText("" + updated.getIndice( ));
					el.getChild("update").setText(
						DateUtils.parseDate(updated.getDate( )));
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
