package app.utils;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static app.utils.Constants.DATE_PATTERN;

public class DataHelper {

    public DataHelper() {

    }

    public LocalDate getLocalDateFromDateString(String date) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        return LocalDate.parse(date, formatter);
    }
}