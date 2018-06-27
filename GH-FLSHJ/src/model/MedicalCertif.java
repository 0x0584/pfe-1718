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

public class MedicalCertif extends DAObject<MedicalCertif> {
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
	public void add( ) {
		String query = "insert into medical value('" + empl_ref + "','" + id
						+ "','" + DateUtil.parseDate(from) + "','" + ndays
						+ "','" + period + "');";
		System.err.println(query);
		new DAO( ).exec(query, true);
	}

	@Override
	public void update(MedicalCertif u) {
		String query = "update medical set fromm='" + DateUtil.parseDate(u.from)
						+ "', ndays='" + u.ndays + "',period='" + u.period + "'"
						+ " where refe='" + empl_ref + "' and id='" + id + "'";
		System.err.println(query);
		new DAO( ).exec(query, true);
	}

	@Override
	public void remove( ) {
		String query = "delete from medical where refe='" + empl_ref
						+ "' and id='" + id + "'";
		System.err.println(query);
		new DAO( ).exec(query, true);
	}

	public static TableModel getMedicalModel(Employee empl) {
		DefaultTableModel model = new DefaultTableModel( );
		for (String col : new String[] {
						"من", "إلى", "عدد الأيام", "عطلة"
		}) {
			model.addColumn(col);
		}
		for (MedicalCertif c : getMedicalCertifs(
			empl.getEmployeeReference( ))) {
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

	public static ArrayList<MedicalCertif> getMedicalCertifs(String ref) {

		ArrayList<MedicalCertif> tmp = new ArrayList<MedicalCertif>( );
		String query = "select * from medical where refe = '" + ref
						+ "' order by id desc";
		System.err.println(query);
		ResultSet r = new DAO( ).exec(query, false);

		try {
			while (r.next( )) {
				MedicalCertif m = new MedicalCertif( );
				m.setEmployeeReference(ref);
				m.setId(r.getInt("id"));
				m.setFrom(DateUtil.parseDate(r.getString("fromm")));
				m.setNumberOfDays(r.getInt("ndays"));
				m.setPeriod(r.getString("period"));

				tmp.add(m);
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage( ));
		}
		return tmp;
	}

}
