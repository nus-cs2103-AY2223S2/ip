package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import errors.DukeInvalidCommandException;
import ui.Response;


/**
 * Utility class which contains functions for utility purposes, such as conversion of date and times
 * @author Nicholas Lee
 */
public class Utility {
    // For user

    /**
     * Converts a user input string of format "d/M/yyyy HHmm" to a LocalDateTime object.
     *
     * @param dateTimeString a string representation of the date and time in format "d/M/yyyy HHmm"
     * @return a LocalDateTime object representing the date and time specified in the input string
     */
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


    /**
     * Takes a LocalDateTime object as input and converts it to a string
     * in the format "MMM dd yyyy h:mma" (e.g. Jan 01 2023 12:00AM)
     *
     * @param dateTime the LocalDateTime object to be converted
     * @return a string representation of the LocalDateTime object in the specified format for displaying to the user
     */
    public static String getDateTimeString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma");
        return dateTime.format(formatter);
    }


    // For read-write operations

    /**
     * Converts a string read from the task file of format "d/M/yyyy HHmm" to a LocalDateTime object.
     *
     * @param dateTimeString a string representation of the date and time in format "d/M/yyyy HHmm"
     * @return a LocalDateTime object representing the date and time specified in the input string
     */
    public static LocalDateTime convertStringToDateTime(String dateTimeString) {
        LocalDateTime dateTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm")
            .withResolverStyle(ResolverStyle.LENIENT);
        dateTime = LocalDateTime.parse(dateTimeString, formatter);
        return dateTime;
    }


    /**
     * Takes a LocalDateTime object as input and converts it to a string
     * in the format "MMM dd yyyy h:mma" (e.g. Jan 01 2023 12:00AM)
     *
     * @param dateTime the LocalDateTime object to be converted
     * @return a string representation of the LocalDateTime object for writing to the task file
     */
    public static String convertDateTimeToString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return dateTime.format(formatter);
    }
}
