package app;

import java.util.Date;

import app.utils.DateUtil;

public enum Period {
	TODAY(0, "اليوم"), ONE_DAY(1, "غداً"),

	ONE_WEEK(7, "الأسبوع المقبل"), TWO_WEEKS(14, "الأسبوعين"),

	ONE_MONTH(30, "شهر"), THREE_MONTHS(60, "شهرين"),

	ONE_SEMESTRE(30 * 6, "أسدس"),

	ONE_YEAR(365, "سنة"), FIVE_YEARS(5 * 365, "خمس سنوات");

	private int value;
	private String title;

	Period(int value, String title) {
		this.value = value;
		this.title = title;
	}

	public static Date getDate(Period p) {
		return DateUtil.add(ONE_DAY, new Date( ), p.value);
	}

	@Override
	public String toString( ) {
		return title;
	}

	public static Period parse(String string) {
		for (Period p : values( )) {
			if (p.toString( ).compareTo(string) == 0) return p;
		}

		return ONE_SEMESTRE;
	}

}
