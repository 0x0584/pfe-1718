package model;

public class Repayment {
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
		return ndays - repayed_days;
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

}
