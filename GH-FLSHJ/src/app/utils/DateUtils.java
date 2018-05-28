package app.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

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

	public static Date addMonths(Date date, int n) {
		try {
			return new DateUtils( ).fmt
							.parse(LocalDate.now( ).plusMonths(n).toString( ));
		} catch (ParseException e) {
			System.err.println(e.getMessage( ));
			return null;
		}
	}

	public static Date addDays(Date date, int n) {
		try {
			return new DateUtils( ).fmt
							.parse(LocalDate.now( ).plusDays(n).toString( ));
		} catch (ParseException e) {
			System.err.println(e.getMessage( ));
			return null;
		}
	}

}
