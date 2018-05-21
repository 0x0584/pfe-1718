package model;

import java.util.ArrayList;
import java.util.Date;

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

	protected Cadre cadre;
	protected String ref, CIN;
	// financial status
	protected String fstatus;
	protected String mission, reason, notes;
	// previous and current jobs
	protected String pjob, cjob;
	// hiring and joining date
	protected Date hdate, jdate;
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
		this.name = "Anas";
		this.fname = "Rchid";
		this.ismoroccan = true;
		this.ref = "124511202";
		this.hdate = new Date( );

		uplifts = Uplift.getUpliftsHistory(jdate);
		
		// TODO: fill employee from xml based on the reference
		new XmlFile().setEmployee(this, ref);
		
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

}