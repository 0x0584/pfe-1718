package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.jdom2.Element;

import xml.XmlFile;

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
		this.diplomas = Employee.getDiplomas(this);
	}

	public AdministrativeStatus getAstatus( ) {
		return astatus;
	}

	public void setAstatus(AdministrativeStatus astatus) {
		this.astatus = astatus;
	}

	public static ArrayList<Diploma> getDiplomas(Employee empl) {
		ArrayList<Diploma> tmp = new ArrayList<Diploma>( );
		// List<Element> list = rootNode.getChildren();
		// XmlSchema xs = XmlSchema.initSet(Diploma.class);

		Iterator<Element> i = new XmlFile( ).getRoot( ).getChildren( )
						.iterator( );

		while (i.hasNext( )) {
			Element e = i.next( );
			Diploma d = new Diploma( );
			d.setTitle(e.getChildText(""));
//			d.setInstitue(institue);
//			d.setMention(mention);
//			d.setSession(session);
			tmp.add(d);
		}

		return tmp;
	}
}