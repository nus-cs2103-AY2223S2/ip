package duke.task;

import duke.exception.InvalidFormatException;

import duke.Parser;

import java.time.LocalDate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Deadline extends Task {
    public static final Pattern PATTERN = Pattern.compile("(.+) /by (.+)");
    protected LocalDate by;
    protected Parser parser;


    public static InvalidFormatException getInvalidFormatException() {
        return new InvalidFormatException("deadline name /by yyyy-MM-dd");
    }

    /**
     * Creates a Deadline object, with default regex pattern
     *
     * @param input String formatted in log file format
     * @param parser To read the date
     * @param isDone To mark if task is done
     * @return a Deadline object
     * @throws InvalidFormatException if the input cannot be parsed
     */
    public static Deadline factoryMethod(String input, Parser parser, boolean isDone) throws InvalidFormatException{
        return factoryMethod(input, parser, isDone, PATTERN);
    }

    /**
     * Creates a Deadline object, with custom regex pattern
     *
     * @param input String formatted in log file format
     * @param parser To read the date
     * @param isDone To mark if task is done
     * @return a Deadline object
     * @throws InvalidFormatException if the input cannot be parsed
     */
    public static Deadline factoryMethod(String input, Parser parser, boolean isDone, Pattern pattern)
            throws InvalidFormatException{

        Matcher m = pattern.matcher(input);
        if (!m.find()) {
            throw getInvalidFormatException();
        }
        return new Deadline(m.group(1), m.group(2), parser, isDone);
    }

    public Deadline(String description, String by, Parser parser, boolean isDone) {
        super(description, isDone);
        this.by = parser.parseDate(by);
        classIcon = "D";
        this.parser = parser;
    }

    @Override
    public String toString() {
        return String.format("%s By: %s", super.toString(), parser.dateToOutputFormat(by));
    }

    @Override
    public String toStringLogFormat() {
        return String.format("%s %s", super.toString(), by);
    }
}