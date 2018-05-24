package app;

public enum SearchField {
	Nom("الإسم", "name", "personal"),

	Prenom("النسب", "familyname", "personal"),

	SOM("ر.ت.", "reference", "administrative"),

	CIN("ب.ت.و.", "cin", "administrative");

	private String title, xtag, parent;

	private SearchField(String title, String xtag, String parent) {
		setTitle(title);
		setXmlTag(xtag);
		setParent(parent);
	}

	public String getTitle( ) {
		return title;
	}

	private void setTitle(String title) {
		this.title = title;
	}

	public String getXmlTag( ) {
		return xtag;
	}

	private void setXmlTag(String xtag) {
		this.xtag = xtag;
	}

	public String getParent( ) {
		return parent;
	}

	private void setParent(String parent) {
		this.parent = parent;
	}

	public String toString( ) {
		return title;
	}
}
