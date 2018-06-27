package app;

public enum SearchField {
	Nom("الإسم", "fisrt_name", "personal"),

	Prenom("النسب", "last_name", "personal"),

	SOM("ر.ت.", "refe", "administrative"),

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
