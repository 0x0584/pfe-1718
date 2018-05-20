package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.swing.table.DefaultTableModel;
import org.jdom2.Element;

import xml.XmlFile;

public class Employee extends Person {
	public static enum Cadre {
		C_1("cadre un"), C_2("cadre deux");
		private String title;

		private Cadre(String title) {
			this.setTitle(title);
		}

		public String getTitle( ) {
			return title;
		}

		private void setTitle(String title) {
			this.title = title;
		}
	}

	public static enum Type {
		Normal, Prof, All;
		public static Type filter(boolean a, boolean b) {
			Type type;

			if (a && b) {
				type = Type.All;
			} else if (a) {
				type = Type.Prof;
			} else if (b) {
				type = Type.Normal;
			} else {
				type = Type.All;
			}

			return type;
		}
	}

	protected Cadre cadre;
	protected String ref, CIN; /* rent number */
	protected String fstatus; /* financial status */
	protected String mission, reason, notes;
	protected String pjob, cjob; /* previous and current jobs */
	protected Date hdate, jdate; /* hiring and joining date */
	protected ArrayList<Uplift> uplifts;
	protected ArrayList<Diploma> diplomas;

	/**
	 * 
	 */
	public Employee() {
		super( );
		this.name = "Anas";
		this.fname = "Rchid";
		this.ismoroccan = true;
		this.ref = "124511202";
		this.hdate = new Date( );
		uplifts = Uplift.getUpliftsHistory(jdate);

		// uplifts.get(uplifts.size( )); the current grade
	}

	public Employee(String ref) {
		super( );
		this.ref = ref;

		// TODO: fill this employee based on the reference
		uplifts = Uplift.getUpliftsHistory(jdate);
	}

	public ArrayList<Diploma> getDiplomas( ) {
		return diplomas;
	}

	public String getReference( ) {
		return ref;
	}

	public String getFinancialstatus( ) {
		return fstatus;
	}

	public String getMission( ) {
		return mission;
	}

	public String getShiftingReason( ) {
		return reason;
	}

	public String getNotes( ) {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getPreviousjob( ) {
		return pjob;
	}

	public void setPreviousjob(String pjob) {
		this.pjob = pjob;
	}

	public String getCurrentjob( ) {
		return cjob;
	}

	public void setCurrentjob(String cjob) {
		this.cjob = cjob;
	}

	public Date getHiringdate( ) {
		return hdate;
	}

	public void setHiringdate(Date hdate) {
		this.hdate = hdate;
	}

	public Date getJoiningDate( ) {
		return jdate;
	}

	public void setJoiningDate(Date jdate) {
		this.jdate = jdate;
	}

	public ArrayList<Uplift> getUplifts( ) {
		return uplifts;
	}

	public Cadre getCadre( ) {
		return cadre;
	}

	public void setCadre(Cadre cadre) {
		this.cadre = cadre;
	}

	public String getCIN( ) {
		return CIN;
	}

	public void setCIN(String cIN) {
		CIN = cIN;
	}

	/* TODO: fix column names */
	public static DefaultTableModel getModelFromXml(Type t) {
		DefaultTableModel model = new DefaultTableModel( );
		String scale = null, echlon = null;
		Iterator<Element> ifoo, ibar; /* temporary iterators */
		Element foo, bar; /* temporary elements */

		/* add columns to the model */
		model.addColumn("ر.ت.");
		model.addColumn("ب.ت.و.");
		model.addColumn("الإسم");
		model.addColumn("النسب");
		model.addColumn("السلم");
		model.addColumn("الرتبة");
		model.addColumn("عدد الشواهد");

		/* loop over the employee */
		ifoo = new XmlFile( ).getRoot( ).getChildren( ).iterator( );
		while (ifoo.hasNext( )) {
			foo = ifoo.next( );
			/* skip employee based on filter */
			if (t == Type.Normal && foo.getAttributeValue("department")
							.compareTo("nil") != 0) {
				continue; /* this means that this is a professor */
			} else if (t == Type.Prof && foo.getAttributeValue("department")
							.compareTo("nil") == 0) {
				continue; /* this means that this is a normal one */
			}
			/* get current scale and echlon */
			ibar = foo.getChild("administrative").getChildren("uplift")
							.iterator( );
			while (ibar.hasNext( )) {
				bar = ibar.next( );
				if (bar.getAttributeValue("state").compareTo("current") == 0) {
					scale = bar.getChildText("scale");
					echlon = bar.getChildText("echlon");
					break;
				}
			}

			/* add rows */
			model.addRow(new String[] {
							foo.getAttributeValue("reference"),
							foo.getChild("administrative").getChildText("cin"),
							foo.getChild("personal").getChildText("name"),
							foo.getChild("personal").getChildText("familyname"),
							scale, echlon, String.format(
								"%d", foo.getChildren("diplomas").size( ))
			});
		}

		return model;
	}

}