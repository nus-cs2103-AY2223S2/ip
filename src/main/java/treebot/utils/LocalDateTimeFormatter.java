package treebot.utils;

import treebot.exception.TreeBotException;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeFormatter {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    public LocalDateTime format(String string) throws TreeBotException {
        try {
            return LocalDateTime.parse(string, formatter);
        } catch (DateTimeException e) {
            throw new TreeBotException("Invalid date time format");
        }
    }
}
