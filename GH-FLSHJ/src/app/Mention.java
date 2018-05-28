package app;

public enum Mention {
	/* passable 10 11.99 */
	PSSBL("Passable"),
	/* assez bien 12 - 13.99 */
	ABIEN("Assez Bien"),
	/* bien 14 - 15.99 */
	BIEN("Bien"),
	/* très bien 16 - 20 */
	TBIEN("Très bien"),
	/* Einstein */
	OTHER("UNDEFINED");

	private String title;

	Mention(String title) {
		setTitle(title);
	}

	public String getTitle( ) {
		return title;
	}

	private void setTitle(String title) {
		this.title = title;
	}

	private static boolean cmp(Mention m, String s) {
		return s.toLowerCase( ).compareTo(m.toString( ).toLowerCase( )) == 0;
	}

	public static Mention parseMention(String str) {
		if (cmp(PSSBL, str)) return PSSBL;
		if (cmp(ABIEN, str)) return ABIEN;
		if (cmp(BIEN, str)) return BIEN;
		if (cmp(TBIEN, str)) return TBIEN;

		return OTHER;
	}

	public String toString( ) {
		return title;
	}
};