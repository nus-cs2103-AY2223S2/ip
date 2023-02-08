package duke.task;

import duke.Parser;
import duke.exception.InvalidFormatException;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Event extends Task {
    public static final Pattern PATTERN = Pattern.compile("(.+) /from (.+) /to (.+)");
    protected LocalDate to;
    protected LocalDate from;

    public static InvalidFormatException getInvalidFormatException() {
        return new InvalidFormatException("event name /from yyyy-MM-dd /to yyyy-MM-dd");
    }

    public static Event factoryMethod(String input, Parser parser, boolean isDone) throws InvalidFormatException {
        Matcher m = Pattern.compile("(.+) (.+) (.+)").matcher(input);
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
    }

    public Event(String description, String from, String to, Parser parser) {
        this(description, from, to, parser,false);
    }

    @Override
    public String toString() {
        return String.format(
                "%s From: %s To: %s", super.toString(), to, from);
    }

    @Override
    public String toString(Parser parser) {
        return String.format("%s %s %s",
                super.toString(),
                parser.dateToLogFormat(to),
                parser.dateToLogFormat(from));
    }
}