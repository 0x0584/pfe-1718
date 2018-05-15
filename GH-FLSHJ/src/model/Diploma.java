package model;

import xml.XmlSet;

public class Diploma {
	private String title, institue;
	private String session; /* e.g. 2017-2018 */
	private Mention mention;
	private XmlSet xtags;

	public Diploma() {
		super( );
		xtags = new XmlSet("diplomas", new String[] {
						"diploma", "institute"
		}, new String[] { });
	}

	public Diploma(	String title, String institue, String session,
					Mention mention) {
		super( );
		this.title = title;
		this.institue = institue;
		this.session = session;
		this.mention = mention;
	}

	@Override
	public String toString( ) {
		return "Diploma [title=" + title + ", institue=" + institue
						+ ", session=" + session + ", mention=" + mention + "]";
	}

	public String getTitle( ) {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getInstitue( ) {
		return institue;
	}

	public void setInstitue(String institue) {
		this.institue = institue;
	}

	public String getSession( ) {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public Mention getMention( ) {
		return mention;
	}

	public void setMention(Mention mention) {
		this.mention = mention;
	}
}
