package app;

import java.util.Date;

import app.utils.DateUtil;

public enum InNext {
	_0(0), _1(1), _2(2), _3(3), _4(4);
	String title;
	int years_to_add;
	Date date;

	@SuppressWarnings("deprecation")
	InNext(int yta) {
		this.years_to_add = yta;
		this.date = DateUtil.add(Period.ONE_YEAR, new Date( ), yta);
		this.title = "" + (date.getYear( ) - 100 + 2000);
	}

	public Date getDate( ) {
		return date;
	}

	@Override
	public String toString( ) {
		return title;
	}

	
	@SuppressWarnings("deprecation")
	public static Date parse(int i) {
		Date d = new Date();
		d.setYear(i);
		return d;
	}
}
