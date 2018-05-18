package model;

import java.util.ArrayList;
import java.util.Date;

public class Uplift {
	/**
	 * 
	 */
	private final static byte RANK[] = new byte[] {
					/* 4 years */
					1, 1, 1, 1,
					/* 8 years */
					2, 2, 3, 3,
					/* 11 years */
					4, 4, 3
	}, GRADE[] = new byte[] {
					9, 10, 11, 12
	};
	private String indice;
	private Date date;
	private byte grade;
	private byte rank;

	/**
	 * @param indice
	 * @param date
	 * @param grade
	 * @param rank
	 */
	public Uplift(String indice, Date date, byte grade, byte rank) {
		super( );
		this.indice = indice;
		this.date = date;
		this.grade = grade;
		this.rank = rank;
	}

	/**
	 * @param hiringdate
	 * @return
	 */
	public static ArrayList<Uplift> getUpliftsHistory(Date hiringdate) {
		ArrayList<Uplift> tmp = new ArrayList<Uplift>( );
		tmp.add(new Uplift("152/2018", new Date( ), (byte) 9, (byte) 4));
		return tmp;

		// TODO: this should be filled up automatically
		// using RANK and GRADE
	}

	@Override
	public String toString( ) {
		return "Uplift [grade=" + grade + ", rank=" + rank + ", indice="
						+ indice + ", date=" + date + "]";
	}

	public short getGrade( ) {
		return grade;
	}

	// public void setGrade(short grade) {
	// this.grade = grade;
	// }

	public short getRank( ) {
		return rank;
	}

	// public void setRank(short rank) {
	// this.rank = rank;
	// }

	public String getIndice( ) {
		return indice;
	}

	// public void setIndice(String indice) {
	// this.indice = indice;
	// }

	public Date getDate( ) {
		return date;
	}
	//
	// public void setDate(Date date) {
	// this.date = date;
	// }

}
