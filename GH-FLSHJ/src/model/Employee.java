package model;

import java.util.ArrayList;
import java.util.Date;

import app.Cadre;
import app.utils.XmlFile;

public class Employee extends Person {
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
	protected ArrayList<MedicalCertif> certifs;
	protected ArrayList<Repayment> repayments;

	public Employee() {
		this("0112358");
	}

	public Employee(String ref) {
		super( );
		XmlFile.initEmployee(this, ref);
	}

	public void setReference(String ref) {
		this.ref = ref;
	}

	public String getFinancialStatus( ) {
		return fstatus;
	}

	public ArrayList<MedicalCertif> getCertifs( ) {
		return certifs;
	}

	public ArrayList<Repayment> getRepayments( ) {
		return repayments;
	}

	public void setRepayments(ArrayList<Repayment> repayments) {
		this.repayments = repayments;
	}

	public void setMedicalCertifs(ArrayList<MedicalCertif> certifs) {
		this.certifs = certifs;
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