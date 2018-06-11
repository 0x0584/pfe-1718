package app;

import java.util.Date;

import app.utils.DateUtils;

public enum Period {
	ONE_DAY(1),

	ONE_WEEK(7), TWO_WEEKS(14),

	ONE_MONTH(30), THREE_MONTHS(60), ONE_SEMESTRE(30 * 6),

	ONE_YEAR(365), FIVE_YEARS(5 * 365);

	private int value;
	private String title;
	
	Period(int value) {
		this.value = value;
		this.title = name( );
	}
	
	Period(int value, String title) {
		this.value = value;
		this.title = title;
	}

	// this is bad code
	// TODO: make this shit better
	public static Date getDate(Period p) {
		return DateUtils.add(ONE_DAY, new Date( ), p.value);
	}
	
	@Override
	public String toString() {
		return title;
	}

	public static Period parse(String string) {
		for(Period p : values( )) {
			if(p.name( ).compareTo(string) == 0) {
				return p;
			}
		}
		
		return ONE_SEMESTRE;
	}
	
}
