package model;

import java.util.Date;

public class Person {
	protected String name, fname, addr, phone;
	protected String bplace;
	protected Date bday;
	protected boolean ismoroccan, ismarried;
	protected String partnerName, partnerJob;
	protected short nchildren;

	public Person() {
		super( );
	}

	/**
	 * @param name
	 * @param fname
	 * @param addr
	 * @param phone
	 * @param bplace
	 * @param bday
	 * @param ismoroccan
	 * @param ismarried
	 * @param nchildren
	 */
	public Person(	String name, String fname, String addr, String phone,
					String bplace, Date bday, boolean ismoroccan,
					boolean ismarried, short nchildren) {
		super( );
		this.name = name;
		this.fname = fname;
		this.addr = addr;
		this.phone = phone;
		this.bplace = bplace;
		this.bday = bday;
		this.ismoroccan = ismoroccan;
		this.ismarried = ismarried;
		this.nchildren = nchildren;
		// TODO: partner setup
	}

	@Override
	public String toString( ) {
		return "Person [name=" + name + ", fname=" + fname + ", addr=" + addr
						+ ", phone=" + phone + ", bplace=" + bplace + ", bday="
						+ bday + ", ismoroccan=" + ismoroccan + ", ismarried="
						+ ismarried + ", nchildren=" + nchildren + ", diplomas="
						+ "]";
	}

	public String getName( ) {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFamilyname( ) {
		return fname;
	}

	public void setFamilyname(String fname) {
		this.fname = fname;
	}

	public String getAddress( ) {
		return addr;
	}

	public void setAddress(String addr) {
		this.addr = addr;
	}

	public String getPhone( ) {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBirthPlace( ) {
		return bplace;
	}

	public void setBirthPlace(String bplace) {
		this.bplace = bplace;
	}

	public Date getBirthDay( ) {
		return bday;
	}

	public void setBirthDay(Date bday) {
		this.bday = bday;
	}

	public boolean isMoroccan( ) {
		return ismoroccan;
	}

	public void setIsMoroccan(boolean ismoroccan) {
		this.ismoroccan = ismoroccan;
	}

	public boolean isMarried( ) {
		return ismarried;
	}

	public void setIsMarried(boolean ismarried) {
		this.ismarried = ismarried;
	}

	public String getPartnerName( ) {
		return partnerName == null ? "بدون" : partnerName;
	}

	public void setPartnerName(String name) {
		this.partnerName = name;
	}

	public String getPartnerJob( ) {
		return partnerJob == null || partnerJob.compareTo("nil") == 0 ? "بدون"
						: partnerJob;
	}

	public void setPartnerJob(String job) {
		this.partnerJob = job;
	}

	public short getNumberOfchildren( ) {
		return nchildren;
	}

	public void setNumberOfchildren(short nchildren) {
		this.nchildren = nchildren;
	}

}
