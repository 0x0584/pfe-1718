package app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Parser {
	private SimpleDateFormat fmt;

	public Parser() {
		// TODO: choose a good format
		fmt = new SimpleDateFormat("yyyy-MM-dd");
	}

	public static String parseDate(Date date) {
		return new Parser( ).fmt.format(date);
	}

	public static Date parseDate(String str) {
		try {
			return new Parser( ).fmt.parse(str);
		} catch (ParseException e) {
			System.err.println(e.getMessage( ));
			return null;
		}
	}
}
