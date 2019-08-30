package app.utils;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static app.utils.Constants.REGEX_DATE_PATTERN;

public class Validator {
    private Pattern pattern = Pattern.compile(REGEX_DATE_PATTERN);

    public boolean isValidDate(String date, DataHelper helper) {
        Matcher matcher = pattern.matcher(date);
        boolean isValidDatePattern = matcher.matches();
        if(!isValidDatePattern) {
            return false;
        }

        try {
            helper.getLocalDateFromDateString(date);
        } catch (Exception ex) {
            return false;
        }

        return true;
    }
}
