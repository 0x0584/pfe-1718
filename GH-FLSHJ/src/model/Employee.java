package model;

import java.util.ArrayList;
import java.util.Date;

public class Employee extends Person {
	private ArrayList<Diploma> diplomas;
	private AdministrativeStatus astatus;

	public Employee() {
		super( );
	}

	public Employee(String name, String fname, String addr, String phone,
					String bplace, Date bday, boolean ismoroccan,
					boolean ismarried, Person partner, short nchildren) {
		super(name, fname, addr, phone, bplace, bday, ismoroccan, ismarried,
			partner, nchildren);
	}

	public Employee(String name, String fname, String addr, String phone,
					String bplace, Date bday, boolean ismoroccan,
					boolean ismarried, Person partner, short nchildren,
					ArrayList<Diploma> diplomas, AdministrativeStatus astatus) {
		super(name, fname, addr, phone, bplace, bday, ismoroccan, ismarried,
			partner, nchildren);
		this.diplomas = diplomas;
		this.astatus = astatus;
	}

	@Override
	public String toString( ) {
		return "Employee [diplomas=" + diplomas + ", astatus=" + astatus
						+ ", toString()=" + super.toString( ) + "]";
	}

	public ArrayList<Diploma> getDiplomas( ) {
		return diplomas;
	}

	public void setDiplomas(ArrayList<Diploma> diplomas) {
		this.diplomas = diplomas;
	}

	public AdministrativeStatus getAstatus( ) {
		return astatus;
	}

	public void setAstatus(AdministrativeStatus astatus) {
		this.astatus = astatus;
	}

}
