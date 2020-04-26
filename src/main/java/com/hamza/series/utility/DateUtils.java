package com.hamza.series.utility;

import java.text.ParseException;
import java.time.* ;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    // The date formatter
    // - dd:   day in month (number)
    // - MM:   month in year (number)
    // - yyyy: year
    //
    // See this link for details: https://docs.oracle.com/javase/tutorial/i18n/format/simpleDateFormat.html
    //
    //
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
    private static DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");


    // read a date string and parse/convert to a date
    public static LocalDate parseDate(String dateStr) throws ParseException {
        LocalDate localDate = LocalDate.parse(dateStr, formatter);

        return localDate;
    }

    // read a date and format/convert to a string
    public static String formatDate(LocalDate theDate) {

        String result = null;

        if (theDate != null) {
            result = formatter.format(theDate);
        }

        return result;
    }
    //////////////////////////////////////Time Parsing ////////////////////////////

    public static LocalTime parseTime(String timeStr) throws ParseException {
        LocalTime localTime = LocalTime.parse(timeStr, formatterTime);

        return localTime;
    }

    // read a date and format/convert to a string
    public static String formatTime(LocalTime theTime) {

        String result = null;

        if (theTime != null) {
            result = formatterTime.format(theTime);
        }

        return result;
    }
}
