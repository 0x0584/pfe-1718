package app.utils;

public abstract class DAObject<T> {
	/**
	 * this is the reference of the Employee
	 */
	protected String empl_ref;

	public String getEmployeeReference( ) {
		return empl_ref;
	}

	public void setEmployeeReference(String ref) {
		this.empl_ref = ref;
	}

	/**
	 * Append the object to the XML file.
	 *
	 * @return true if it succeeded
	 */
	public abstract void add( );

	/**
	 * @param updated
	 *            T object to replace the old XML element with.
	 * 
	 * @return true if it succeeded
	 */
	public abstract void update(T updated);

	/**
	 * removes the object from the XML file
	 *
	 * @return true if it succeeded
	 */
	public abstract void remove( );
}
