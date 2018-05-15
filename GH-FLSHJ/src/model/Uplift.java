package model;

import java.util.Date;

public class Uplift {
	private short grade, rank;
	private String indice;
	private Date date;

	public Uplift() {
		super( );
	}

	public Uplift(short grade, short rank, String indice, Date date) {
		super( );
		this.grade = grade;
		this.rank = rank;
		this.indice = indice;
		this.date = date;
	}

	@Override
	public String toString( ) {
		return "Uplift [grade=" + grade + ", rank=" + rank + ", indice="
						+ indice + ", date=" + date + "]";
	}

	public short getGrade( ) {
		return grade;
	}

	 public void setGrade(short grade) {
	 this.grade = grade;
	 }

	public short getRank( ) {
		return rank;
	}

	 public void setRank(short rank) {
	 this.rank = rank;
	 }

	public String getIndice( ) {
		return indice;
	}

	 public void setIndice(String indice) {
	 this.indice = indice;
	 }

	public Date getDate( ) {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
