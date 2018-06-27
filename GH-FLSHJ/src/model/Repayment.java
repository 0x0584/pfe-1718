package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import app.utils.DAO;
import app.utils.DAObject;

public class Repayment extends DAObject<Repayment> {
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
		return Math.abs(ndays - repayed_days);
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
	public void add( ) {
		String query = "insert into repayment value('" + empl_ref + "','" + id
						+ "','" + repayed_days + "','" + ndays + "','" + period
						+ "');";
		System.err.println(query);
		new DAO( ).exec(query, true);
	}

	@Override
	public void update(Repayment u) {
		String query = "update repayment set repayed='" + u.repayed_days
						+ "', ndays='" + u.ndays + "',period='" + u.period
						+ "' where refe='" + empl_ref + "' and id='" + id + "'";
		System.err.println(query);
		new DAO( ).exec(query, true);
	}

	@Override
	public void remove( ) {
		String query = "delete from repayment where refe='" + empl_ref
						+ "' and id='" + id + "'";
		System.err.println(query);
		new DAO( ).exec(query, true);
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
							r.getPeriod( ), "" + r.getRepayedDays( ),
							"" + r.getNumberOfDays( ), "" + r.getRest( )
			});
		}

		return model;
	}

	public static ArrayList<Repayment> getRepayments(String ref) {

		ArrayList<Repayment> tmp = new ArrayList<Repayment>( );
		String query = "select * from repayment where refe = '" + ref
						+ "' order by id desc";
		System.err.println(query);
		ResultSet r = new DAO( ).exec(query, false);

		try {
			while (r.next( )) {
				Repayment rr = new Repayment( );
				rr.setEmployeeReference(ref);
				rr.setId(r.getInt("id"));
				rr.setNumberOfDays(r.getInt("ndays"));
				rr.setPeriod(r.getString("period"));
				rr.setRepayedDays(r.getInt("repayed"));
				tmp.add(rr);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage( ));
		}

		return tmp;
	}

}
