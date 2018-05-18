package model;

public class Professor extends Employee {
	private String dep;

	/**
	 * 
	 */
	public Professor() {
		super( );
	}

	/**
	 * @param ref
	 */
	public Professor(String ref) {
		super(ref);
		// TODO fill professor based on ref
		// xml.getProfessor(ref);
	}

	public String getDepartment( ) {
		return dep;
	}

	public void setDepartment(String dep) {
		this.dep = dep;
	}

}
