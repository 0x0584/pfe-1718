package app;

public enum Holiday {
	/* administrative */
	NORMAL("Administrative"),

	/* both are exceptional except that the last one TO_QUIT has no reason */
	EXCEP("Exceptionnelle"), TO_QUIT("Quitter le territoire");

	private String title;

	private Holiday(String title) {
		setTitle(title);
	}

	public String getTitle( ) {
		return title;
	}

	private void setTitle(String title) {
		this.title = title;
	}

	public String toString() {
		return title;
	}
}
