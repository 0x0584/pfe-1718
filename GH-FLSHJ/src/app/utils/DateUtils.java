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

	/**
	 * @param date
	 *            date to parse as string
	 * @return string of the parsed date
	 */
	public static String parseDate(Date date) {
		return new DateUtils( ).fmt.format(date);
	}

	/**
	 * @param from
	 *            Starting date
	 * @param to
	 *            end date
	 * @return String as from - to
	 */
	public static String parseDate(Date from, Date to) {
		return String.format("%s - %s", parseDate(from), parseDate(to));
	}

	/**
	 * @param str
	 *            formated date as string
	 * @return new Date based on the parsed string
	 */
	public static Date parseDate(String str) {
		try {
			return new DateUtils( ).fmt.parse(str);
		} catch (ParseException e) {
			System.err.println(e.getMessage( ));
			return null;
		}
	}

	/**
	 * @param p
	 *            any period beside ONE_MONTH, ONE_YEAR would be considered as
	 *            ONE_DAY
	 * @param date
	 *            to add to
	 * @param n
	 *            number of things to add based on the indicated period
	 * @return new Date
	 */
	public static Date add(Period p, Date date, int n) {
		int type = 0;

		if (p.equals(Period.ONE_MONTH)) {
			type = Calendar.MONTH;
		} else if (p.equals(Period.ONE_YEAR)) {
			type = Calendar.YEAR;
		} else {
			type = Calendar.DATE;
		}

		Calendar c = Calendar.getInstance( );
		c.setTime(date);
		c.add(type, n);

		return c.getTime( );

	}

	/**
	 * @param from
	 *            date
	 * @param to
	 *            date
	 * @return absolute number of days in difference
	 */
	public static long diffAbs(Date from, Date to) {
		if (from != null && to != null) {
			long diff = Math.abs(from.getTime( ) - to.getTime( ));
			return TimeUnit.MILLISECONDS.toDays(diff);
		} else return 0;
	}

	/**
	 * @param from
	 *            date
	 * @param to
	 *            date
	 * @return number of days in difference, it could be negative
	 */
	public static long diff(Date from, Date to) {
		if (from != null && to != null) {
			long diff = to.getTime( ) - from.getTime( );
			return TimeUnit.MILLISECONDS.toDays(diff);
		} else return 0;
	}
}
