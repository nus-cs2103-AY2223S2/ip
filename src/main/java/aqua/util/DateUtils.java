package aqua.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import aqua.exception.IllegalSyntaxException;


public class DateUtils {
    public static LocalDateTime parse(String dateString) throws IllegalSyntaxException {
        try {
            return LocalDateTime.parse(dateString);
        } catch (DateTimeParseException parseEx) {
            try {
                if (dateString.matches("\\d{4}-\\d{1,2}-\\d{1,2} \\d{4}")) {
                    return LocalDateTime.parse(
                        dateString,
                        DateTimeFormatter.ofPattern("yyyy-M-d HHmm")
                    );
                } else if (dateString.matches("\\d{4}-\\d{1,2}-\\d{1,2}")) {
                    return LocalDateTime.parse(
                        dateString + " 0000",
                        DateTimeFormatter.ofPattern("yyyy-M-d HHmm")
                    );
                }
            } catch (DateTimeParseException ex) {
                // random numbers eg. 0000-00-00
                throw new IllegalSyntaxException(
                    String.format("I do not understand when this is [%s]", dateString)
                );
            }
        }
        throw new IllegalSyntaxException(
            String.format("I do not understand when this is [%s]", dateString)
        );
    }


    public static String formatNice(LocalDateTime time) {
        return time.format(DateTimeFormatter.ofPattern("d LLL yyyy HHmm"));
    }
}
