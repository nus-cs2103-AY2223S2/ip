package utils;
import errors.DukeInvalidCommandException;
import formatters.Response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;


public class Utility {


    public static LocalDateTime parseDateTime(String dateTimeString) throws DukeInvalidCommandException {
        LocalDateTime dateTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm").withResolverStyle(ResolverStyle.LENIENT);;
        try {
            dateTime = LocalDateTime.parse(dateTimeString, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeInvalidCommandException(Response.DATE_FORMAT_INCORRECT.toString());
        }
        return dateTime;
    }

    public static String getDateTimeString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma");
        return dateTime.format(formatter);
    }











}
