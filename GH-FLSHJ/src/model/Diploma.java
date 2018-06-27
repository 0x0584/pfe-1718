package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import app.utils.DAO;
import app.utils.DAObject;

public class Diploma extends DAObject<Diploma> {
	private int id;
	private String title, institue;
	private String session; // e.g. 2017-2018
	private String mention;

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
					String mention) {
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

	public String getMention( ) {
		return mention;
	}

	public void setMention(String mention) {
		this.mention = mention;
	}

	@Override
	public void add( ) {
		String query = "insert into diploma value('" + empl_ref + "','" + id
						+ "','" + title + "','" + mention.toString( ) + "','"
						+ institue + "','" + session + "');";
		System.err.println(query);
		new DAO( ).exec(query, true);
	}

	@Override
	public void update(Diploma u) {
		String query = "update diploma set title='" + u.title + "', menstion='"
						+ u.mention + "',insti='" + u.institue + "', sess='"
						+ u.session + "'" + " where refe='" + empl_ref
						+ "' and id='" + id + "'";
		System.err.println(query);
		new DAO( ).exec(query, true);
	}

	@Override
	public void remove( ) {
		String query = "delete from diploma where refe='" + empl_ref
						+ "' and id='" + id + "'";
		System.err.println(query);
		new DAO( ).exec(query, true);
	}

	public static TableModel getDiplomasModel(Employee empl) {
		DefaultTableModel model = new DefaultTableModel( );

		for (String str : new String[] {
						"تاريخ الحصول عليها", "المؤسسة",

						"الميزة", "الشهادات",
		}) {
			model.addColumn(str);
		}

		for (Diploma d : getDiplomas(empl.getEmployeeReference( ))) {
			model.addRow(new String[] {
							d.getSession( ), d.getInstitue( ), d.getMention( ),
							d.getTitle( )
			});
		}

		return model;
	}

	public static ArrayList<Diploma> getDiplomas(String refe) {
		ArrayList<Diploma> tmp = new ArrayList<Diploma>( );
		String query = "select * from diploma where refe = '" + refe
						+ "' order by id desc";
		System.err.println(query);
		ResultSet r = new DAO( ).exec(query, false);

		try {
			while (r.next( )) {
				Diploma d = new Diploma( );
				d.setId(r.getInt("id"));
				d.setEmployeeReference(refe);
				d.setInstitue(r.getString("insti"));
				d.setSession(r.getString("sess"));
				d.setMention(r.getString("menstion"));
				d.setTitle(r.getString("title"));
				tmp.add(d);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage( ));
		}

		return tmp;
	}
}
