package duke.task;

import duke.Parser;
import duke.exception.InvalidFormatException;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Deadline extends Task {
    public static final Pattern pattern = Pattern.compile("(.+) /by (.+)");
    protected LocalDate by;

    public static InvalidFormatException getInvalidFormatException() {
        return new InvalidFormatException("deadline name /by yyyy-MM-dd");
    }

    /**
     * Creates a Deadline object using only 1 String argument
     *
     * @param input String formatted in log file format
     * @param parser To read the date
     * @param isDone To mark if task is done
     * @return a Deadline object
     * @throws InvalidFormatException if the input cannot be parsed
     */
    public static Deadline factoryMethod(String input, Parser parser, boolean isDone) throws InvalidFormatException{
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
    }

    public Deadline(String description, String by, Parser parser) {
        this(description, by, parser, false);
    }

    @Override
    public String toString() {
        return String.format("%s By: %s", super.toString(), by);
    }

    @Override
    public String toString(Parser parser) {
        return String.format("%s %s", super.toString(), parser.dateToLogFormat(by));
    }
}