package model;

import java.util.ArrayList;
import java.util.Date;

import xml.XmlFile;

public class Employee extends Person {
	public static enum Cadre {
		C_0("Engenieur 1er Grade"),

		C_1("Technicien 1er Grade"),

		C_2("Technicien 2eme Grade");

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

		public static Cadre parseCadre(String cstr) {
			for (Cadre c : values( )) {
				if (c.title.compareTo(cstr) == 0) return c;
			}

			return C_0;
		}

		public String toString( ) {
			return title;
		}
	}

	protected Cadre cadre;
	protected String ref, CIN, dep;
	// financial status
	protected String fstatus;
	protected String mission, reason, notes;
	// previous and current jobs
	protected String pjob, cjob;
	// hiring and joining date
	protected Date hdate, jdate;
	protected ArrayList<Uplift> uplifts;
	protected ArrayList<Diploma> diplomas;

	public Employee() {
		this("0112358");
	}

	public Employee(String ref) {
		super( );
		XmlFile.setEmployee(this, ref);
	}

	public void setReference(String ref) {
		this.ref = ref;
	}

	public String getFinancialStatus( ) {
		return fstatus;
	}

	public void setFinancialStatus(String fstatus) {
		this.fstatus = fstatus;
	}

	public String getReason( ) {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getPreviousJob( ) {
		return pjob;
	}

	public void setPreviousJob(String pjob) {
		this.pjob = pjob;
	}

	public String getCurrentJob( ) {
		return cjob;
	}

	public void setCurrentJob(String cjob) {
		this.cjob = cjob;
	}

	public void setHiringDate(Date hdate) {
		this.hdate = hdate;
	}

	public void setJoinDate(Date jdate) {
		this.jdate = jdate;
	}

	public void setMission(String mission) {
		this.mission = mission;
	}

	public ArrayList<Uplift> getUplifts( ) {
		return uplifts;
	}

	public void setUplifts(ArrayList<Uplift> uplifts) {
		this.uplifts = uplifts;
	}

	public ArrayList<Diploma> getDiplomas( ) {
		return diplomas;
	}

	public void setDiplomas(ArrayList<Diploma> diplomas) {
		this.diplomas = diplomas;
	}

	public String getReference( ) {
		return ref;
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

	public Date getHiringDate( ) {
		return hdate;
	}

	public Date getJoinDate( ) {
		return jdate;
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

	public void setCIN(String cin) {
		CIN = cin;
	}

	public String getDepartment( ) {
		return dep;
	}

	public void setDepartment(String dep) {
		this.dep = dep.compareTo("nil") == 0 ? null : dep;
	}

	public boolean isProfessor( ) {
		return dep != null;
	}
}