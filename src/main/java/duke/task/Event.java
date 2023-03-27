package duke.task;

import duke.exception.InvalidFormatException;

import duke.Parser;

import java.time.LocalDate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Event extends Task {
    public static final Pattern PATTERN = Pattern.compile("(.+) /from (.+) /to (.+)");
    protected LocalDate to;
    protected LocalDate from;
    protected Parser parser;

    public static InvalidFormatException getInvalidFormatException() {
        return new InvalidFormatException("event name /from yyyy-MM-dd /to yyyy-MM-dd");
    }
    /**
     * Creates a Event object, with default regex pattern
     *
     * @param input String formatted in log file format
     * @param parser To read the date
     * @param isDone To mark if task is done
     * @return a Deadline object
     * @throws InvalidFormatException if the input cannot be parsed
     */
    public static Event factoryMethod(String input, Parser parser, boolean isDone) throws InvalidFormatException{
        return factoryMethod(input, parser, isDone, PATTERN);
    }

    public static Event factoryMethod(String input, Parser parser, boolean isDone, Pattern pattern)
            throws InvalidFormatException {
        Matcher m = pattern.matcher(input);
        if (!m.find()) {
            throw getInvalidFormatException();
        }
        return new Event(m.group(1), m.group(2), m.group(3), parser, isDone);
    }

    public Event(String description, String from, String to, Parser parser, boolean isDone) {
        super(description, isDone);
        this.from = parser.parseDate(from);
        this.to = parser.parseDate(to);
        classIcon = "E";
        this.parser = parser;
    }

    @Override
    public String toString() {
        return String.format(
                "%s From: %s To: %s",
                super.toString(),
                parser.dateToOutputFormat(to),
                parser.dateToOutputFormat(from));
    }

    @Override
    public String toStringLogFormat() {
        return String.format("%s %s %s", super.toString(), to, from);
    }
}