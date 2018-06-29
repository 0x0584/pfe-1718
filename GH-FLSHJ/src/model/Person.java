package model;

import java.util.Date;

import app.utils.DAObject;

public abstract class Person extends DAObject<Employee> {
	protected String name, fname, addr, phone;
	protected String name_ar, fname_ar, addr_ar, bplace_ar;
	protected String bplace;
	protected Date bday;
	protected String ismoroccan, ismarried;
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
					String bplace, Date bday, String ismoroccan,
					String ismarried, short nchildren) {
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
	}

	@Override
	public String toString( ) {
		return "Person [name=" + name + ", fname=" + fname + ", addr=" + addr
						+ ", phone=" + phone + ", bplace=" + bplace + ", bday="
						+ bday + ", ismoroccan=" + ismoroccan + ", ismarried="
						+ ismarried + ", nchildren=" + nchildren + ", diplomas="
						+ "]";
	}

	public String getNameArabic( ) {
		return name_ar;
	}

	public String getFamilyNameArabic( ) {
		return fname_ar;
	}

	public String getAddressArabic( ) {
		return addr_ar;
	}

	public String getBirthPlaceArabic( ) {
		return bplace_ar;
	}

	public void setNameArabic(String name_ar) {
		this.name_ar = name_ar;
	}

	public void setFamilyNameArabic(String fname_ar) {
		this.fname_ar = fname_ar;
	}

	public void setAddressArabic(String addr_ar) {
		this.addr_ar = addr_ar;
	}

	public void setBirthPlaceArabic(String bplace_ar) {
		this.bplace_ar = bplace_ar;
	}

	public String getName( ) {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFamilyName( ) {
		return fname;
	}

	public void setFamilyName(String fname) {
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

	public String isMoroccan( ) {
		return ismoroccan;
	}

	public void setIsMoroccan(String ismoroccan) {
		this.ismoroccan = ismoroccan;
	}

	public String isMarried( ) {
		return ismarried;
	}

	public void setIsMarried(String ismarried) {
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

	public short getNumberOfChildren( ) {
		return nchildren;
	}

	public void setNumberOfchildren(short nchildren) {
		this.nchildren = nchildren;
	}

}
