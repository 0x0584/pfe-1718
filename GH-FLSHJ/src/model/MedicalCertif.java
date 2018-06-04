package model;

import java.util.Date;

public class MedicalCertif {
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

}
