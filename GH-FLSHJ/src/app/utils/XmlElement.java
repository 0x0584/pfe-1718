package app.utils;

import org.jdom2.Element;

public abstract class XmlElement<T> {
	/**
	 * this is the reference of the Employee
	 */
	protected String empl_ref;

	public String getEmployeeRefrence( ) {
		return empl_ref;
	}

	public void setEmployeeRefrence(String ref) {
		this.empl_ref = ref;
	}

	/**
	 * Append the object to the XML file.
	 *
	 * @return true if it succeeded
	 */
	public abstract boolean add( );

	/**
	 * @param updated
	 *            T object to replace the old XML element with.
	 * 
	 * @return true if it succeeded
	 */
	public abstract boolean update(T updated);

	/**
	 * removes the object from the XML file
	 *
	 * @return true if it succeeded
	 */
	public abstract boolean remove( );

	/**
	 * This method is supposed to be a static method, but because of teh poor
	 * language design that java has, you cannot create abstract static method
	 * nor override old ones, so yeah, this it a hack
	 * 
	 * @return the Last element of the class T
	 */
	public abstract Element getLast( );

	/**
	 * @return XML element of the corresponding object
	 */
	public abstract Element getElement( );
}
