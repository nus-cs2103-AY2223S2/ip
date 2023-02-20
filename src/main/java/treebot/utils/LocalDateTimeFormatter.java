package treebot.utils;

import treebot.exception.TreeBotException;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a class that formats a given string into LocalDateTime.
 */
public class LocalDateTimeFormatter {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    /**
     * Returns the formatted <code>LocalDateTime</code> object from the given string.
     * @param string string to be formatted
     * @return LocalDateTime
     * @throws TreeBotException
     */
    public LocalDateTime format(String string) throws TreeBotException {
        try {
            return LocalDateTime.parse(string, formatter);
        } catch (DateTimeException e) {
            throw new TreeBotException("Invalid date time format");
        }
    }
}
