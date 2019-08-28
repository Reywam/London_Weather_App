package app.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private final String DATE_PATTERN = "\\d{2}.\\d{2}.\\d{4}";
    private Pattern pattern = Pattern.compile(DATE_PATTERN);

    public boolean isValidDate(String date) {
        Matcher matcher = pattern.matcher(date);
        boolean isValidDatePattern = matcher.matches();
        if(!isValidDatePattern)
        {
            return false;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
        try
        {
            LocalDate localDate = LocalDate.parse(date, formatter);
        }
        catch (Exception ex)
        {
            return false;
        }

        return true;
    }
}
