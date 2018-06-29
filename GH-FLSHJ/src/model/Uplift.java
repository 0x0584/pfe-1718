package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import app.Period;
import app.utils.DAO;
import app.utils.DAObject;
import app.utils.DateUtil;

public class Uplift extends DAObject<Uplift> {
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
	public void add( ) {
		String query = "insert into uplift values( '" + empl_ref + "','" + id
						+ "','" + grade + "','" + rank + "','" + indice + "','"
						+ DateUtil.parseDate(date) + "')";
		System.err.println(query);
		new DAO( ).exec(query, true);
	}

	@Override
	public void update(Uplift u) {
		String query = "update uplift set scalee='" + u.grade + "', echlon='"
						+ u.rank + "',indice='" + u.indice + "', updatee='"
						+ DateUtil.parseDate(u.date) + "'" + " where refe='"
						+ empl_ref + "' and id='" + id + "'";
		System.err.println(query);
		new DAO( ).exec(query, true);
	}

	@Override
	public void remove( ) {
		String query = "delete from uplift where refe='" + empl_ref
						+ "' and id='" + id + "'";
		System.err.println(query);
		new DAO( ).exec(query, true);
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

						"السلم", "الرتبة",
		}) {
			model.addColumn(str);
		}

		for (Uplift u : getUplifts(empl.getEmployeeReference( ))) {
			model.addRow(new String[] {
							DateUtil.parseDate(u.getDate( )), u.getIndice( ),
							"" + u.getGrade( ), "" + u.getRank( ),
			});
		}

		return model;
	}

	public static ArrayList<Uplift> getUplifts(String ref) {
		ArrayList<Uplift> tmp = new ArrayList<Uplift>( );
		String query = "select * from uplift where refe = '" + ref
						+ "' order by id desc";
		System.err.println(query);
		ResultSet r = new DAO( ).exec(query, false);

		try {
			while (r.next( )) {
				Uplift u = new Uplift( );
				u.setEmployeeReference(ref);
				u.setRank(r.getShort("echlon"));
				u.setDate(DateUtil.parseDate(r.getString("updatee")));
				u.setGrade(r.getShort("scalee"));
				u.setIndice(r.getString("indice"));
				tmp.add(u);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage( ));
		}

		return tmp;
	}

	public static Uplift getCurrentUplift(String ref) {
		String query = "select * from uplift where refe = '" + ref
						+ "' order by id desc";
		System.err.println(query);
		ResultSet r = new DAO( ).exec(query, false);

		Uplift u = new Uplift( );

		try {
			while (r.next( )) {
				u.setEmployeeReference(ref);
				u.setRank(r.getShort("echlon"));
				u.setDate(DateUtil.parseDate(r.getString("updatee")));
				u.setGrade(r.getShort("scalee"));
				u.setIndice(r.getString("indice"));
				break;
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage( ));
		}

		return u;
	}

	@SuppressWarnings("deprecation")
	public static TableModel getInNextModel(int i) {
	
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

			if (nextdate.getYear( ) == i) {
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
