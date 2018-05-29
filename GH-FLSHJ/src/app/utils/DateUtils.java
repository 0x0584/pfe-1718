package app.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

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
		try {
			Date foo = null;
			if (p.equals(Period.ONE_MONTH)) {
				foo = new DateUtils( ).fmt.parse(
					LocalDate.now( ).plusMonths(n).toString( ));
			} else if (p.equals(Period.ONE_WEEK)) {
				foo = new DateUtils( ).fmt.parse(
					LocalDate.now( ).plusDays(n * 7).toString( ));
			} else if (p.equals(Period.ONE_YEAR)) {
				foo = new DateUtils( ).fmt.parse(
					LocalDate.now( ).plusYears(n).toString( ));
			} else {
				foo = new DateUtils( ).fmt.parse(
					LocalDate.now( ).plusDays(n).toString( ));
			}

			return foo;
		} catch (ParseException e) {
			System.err.println(e.getMessage( ));
			return null;
		}
	}

	public static long DateDiff(Period p, Date from, Date to) {
		if (from != null && to != null) {
			return (Math.abs(from.getTime( ) - to.getTime( ))
							/ (1000 * 60 * 60 * 24))
							- parseDate(LocalDate.MIN.toString( )).getTime( );
		} else return 0;
	}

}
