package xml;

public enum Files {
	HUMAIN_RESOURCES("hr"), DEPARTMENTS("dep");
	private final static String root = "data/xml/";
	private String fpath;

	private Files(String path) {
		this.setFilePath(root + path + ".xml");
	}

	public String getFilePath( ) {
		return fpath;
	}

	private void setFilePath(String fpath) {
		this.fpath = fpath;
	}
};