package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import errors.DukeInvalidCommandException;
import ui.Response;


public class Utility {

    public static LocalDateTime parseDateTime(String dateTimeString) throws DukeInvalidCommandException {
        LocalDateTime dateTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm")
                .withResolverStyle(ResolverStyle.LENIENT);
        try {
            dateTime = LocalDateTime.parse(dateTimeString, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeInvalidCommandException(Response.DATE_FORMAT_INCORRECT.toString());
        }
        return dateTime;
    }

    // for reading writing
    public static LocalDateTime convertStringToDateTime(String dateTimeString) {
        LocalDateTime dateTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm")
                .withResolverStyle(ResolverStyle.LENIENT);
            dateTime = LocalDateTime.parse(dateTimeString, formatter);
        return dateTime;
    }

//    for reading writing
    public static String convertDateTimeToString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return dateTime.format(formatter);
    }

    // for user
    public static String getDateTimeString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma");
        return dateTime.format(formatter);
    }
}
