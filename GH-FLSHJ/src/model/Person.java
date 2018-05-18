package model;

import java.util.Date;

public class Person {
	protected String name, fname, addr, phone;
	protected String bplace;
	protected Date bday;
	protected boolean ismoroccan, ismarried;
	protected Person partner;
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
	 * @param partner
	 * @param nchildren
	 */
	public Person(	String name, String fname, String addr, String phone,
					String bplace, Date bday, boolean ismoroccan,
					boolean ismarried, Person partner, short nchildren) {
		super( );
		this.name = name;
		this.fname = fname;
		this.addr = addr;
		this.phone = phone;
		this.bplace = bplace;
		this.bday = bday;
		this.ismoroccan = ismoroccan;
		this.ismarried = ismarried;
		this.partner = partner;
		this.nchildren = nchildren;
	}

	@Override
	public String toString( ) {
		return "Person [name=" + name + ", fname=" + fname + ", addr=" + addr
						+ ", phone=" + phone + ", bplace=" + bplace + ", bday="
						+ bday + ", ismoroccan=" + ismoroccan + ", ismarried="
						+ ismarried + ", partner=" + partner + ", nchildren="
						+ nchildren + ", diplomas=" + "]";
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

	public Person getPartner( ) {
		return partner;
	}

	public void setPartner(Person partner) {
		this.partner = partner;
	}

	public short getNumberOfchildren( ) {
		return nchildren;
	}

	public void setNumberOfchildren(short nchildren) {
		this.nchildren = nchildren;
	}

}
