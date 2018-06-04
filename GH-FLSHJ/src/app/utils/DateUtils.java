package app.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import app.Period;

public class DateUtils {
	private SimpleDateFormat fmt;

	public DateUtils() {
		// TODO: choose a good format
		fmt = new SimpleDateFormat("yyyy-MM-dd");
	}

	public static String parseDate(Date date) {
		return new DateUtils( ).fmt.format(date);
	}

	public static String parseDate(Date from, Date to) {
		return String.format("%s - %s", parseDate(from), parseDate(to));
	}

	public static Date parseDate(String str) {
		try {
			return new DateUtils( ).fmt.parse(str);
		} catch (ParseException e) {
			System.err.println(e.getMessage( ));
			return null;
		}
	}

	public static Date add(Period p, Date date, int n) {
		int type = 0;

		if (p.equals(Period.ONE_MONTH)) {
			type = Calendar.MONTH;
		} else if (p.equals(Period.ONE_YEAR)) {
			type = Calendar.YEAR;
		} else if (p.equals(Period.ONE_DAY)) {
			type = Calendar.DATE;
		}
		Calendar c = Calendar.getInstance( );
		c.setTime(date);
		c.add(type, n);
		return c.getTime( );

	}

	public static long DateDiff(Period p, Date from, Date to) {
		if (from != null && to != null) {
			long diff = from.getTime( ) - to.getTime( );
			return TimeUnit.MILLISECONDS.toDays(Math.abs(diff));
		} else return 0;
	}

}
