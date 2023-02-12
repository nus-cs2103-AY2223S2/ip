package utils;

import dukeexception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTime {

    public static LocalDateTime getDateTime(String date) throws DukeException {
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime localDateTime = LocalDateTime.parse(date, dateTimeFormatter);
            return localDateTime;
        } catch (DateTimeParseException e) {
            throw new DukeException("\t Wrong Date Format, please write in yyyy-MM-dd HHmm.\n");
        }
    }
    public static String dateFormatter(String date) throws DukeException {
        try {
            LocalDateTime localDateTime = getDateTime(date);
            return localDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        } catch (DateTimeParseException e) {
            throw new DukeException("\t Wrong Date Format, please write in yyyy-MM-dd HHmm.\n");
        }
    }

}
