package model;

import java.util.ArrayList;
import java.util.Date;

public class AdministrativeStatus {
	private String reference, rent; /* rent number */
	private String fstatus; /* financial status */
	private String mission, reason, notes;
	private String pjob, cjob; /*
								 * previous and current jobs
								 */
	private Date hdate, jdate; /*
								 * hiring and joining date
								 */
	private ArrayList<Uplift> uplifts;

	public AdministrativeStatus() {
		super( );

	}

	public AdministrativeStatus(String reference, String rent, String fstatus,
								String mission, String reason, String notes,
								String pjob, String cjob, Date hdate,
								Date jdate, ArrayList<Uplift> uplifts) {
		super( );
		this.reference = reference;
		this.rent = rent;
		this.fstatus = fstatus;
		this.mission = mission;
		this.reason = reason;
		this.notes = notes;
		this.pjob = pjob;
		this.cjob = cjob;
		this.hdate = hdate;
		this.jdate = jdate;
		this.uplifts = uplifts;
	}

	@Override
	public String toString( ) {
		return "AdministrativeStatus [reference=" + reference + ", rent=" + rent
						+ ", fstatus=" + fstatus + ", mission=" + mission
						+ ", reason=" + reason + ", notes=" + notes + ", pjob="
						+ pjob + ", cjob=" + cjob + ", hdate=" + hdate
						+ ", jdate=" + jdate + ", uplifts=" + uplifts + "]";
	}

	public String getReference( ) {
		return reference;
	}

	// public void setReference(String reference) {
	// this.reference = reference;
	// }

	public String getRent( ) {
		return rent;
	}

//	public void setRent(String rent) {
//		this.rent = rent;
//	}

	public String getFinancialstatus( ) {
		return fstatus;
	}

//	public void setFstatus(String fstatus) {
//		this.fstatus = fstatus;
//	}

	public String getMission( ) {
		return mission;
	}

//	public void setMission(String mission) {
//		this.mission = mission;
//	}

	public String getShiftingReason( ) {
		return reason;
	}

//	public void setReason(String reason) {
//		this.reason = reason;
//	}

	public String getNotes( ) {
		return notes;
	}

//	public void setNotes(String notes) {
//		this.notes = notes;
//	}

	public String getPreviousjob( ) {
		return pjob;
	}

	public void setPjob(String pjob) {
		this.pjob = pjob;
	}

	public String getCurrentjob( ) {
		return cjob;
	}

	public void setCjob(String cjob) {
		this.cjob = cjob;
	}

	public Date getHiringdate( ) {
		return hdate;
	}

	public void setHdate(Date hdate) {
		this.hdate = hdate;
	}

	public Date getJoiningdate( ) {
		return jdate;
	}

	public void setJdate(Date jdate) {
		this.jdate = jdate;
	}

	public ArrayList<Uplift> getUplifts( ) {
		return uplifts;
	}

	public void setUplifts(ArrayList<Uplift> uplifts) {
		this.uplifts = uplifts;
	}

	
}
