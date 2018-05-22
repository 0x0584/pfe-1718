package model;

import xml.XmlFile;

public class Professor extends Employee {
	private String dep;

	public Professor() {
		super( );
		this.dep = "Informatique";
	}

	public Professor(String ref) {
		XmlFile.setProfessor(this, ref);
	}

	public String getDepartment( ) {
		return dep;
	}

	public void setDepartment(String dep) {
		this.dep = dep;
	}

}
