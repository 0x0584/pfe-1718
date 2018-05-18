package model;

import java.util.ArrayList;
import java.util.Date;

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
	};

	private Cadre cadre;
	private String ref, rent, CIN; /* rent number */
	private String fstatus; /* financial status */
	private String mission, reason, notes;
	private String pjob, cjob; /* previous and current jobs */
	private Date hdate, jdate; /* hiring and joining date */
	private ArrayList<Uplift> uplifts;
	private ArrayList<Diploma> diplomas;

	public Employee() {
		super( );
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

	public String getRent( ) {
		return rent;
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
}