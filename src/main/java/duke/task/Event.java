package duke.task;

import duke.Parser;
import duke.exception.InvalidFormatException;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Event extends Task {
    public static final Pattern pattern = Pattern.compile("(.+) /from (.+) /to (.+)");
    protected LocalDate to;
    protected LocalDate from;

    public static InvalidFormatException getInvalidFormatException() {
        return new InvalidFormatException("deadline name /from yyyy-MM-dd /to yyyy-MM-dd");
    }

    public Event(String description, String preFrom, String preTo, Parser p) {
        super(description);
        this.from = LocalDate.parse(preFrom, p.getInputFormat());
        this.to = LocalDate.parse(preTo, p.getInputFormat());
        classIcon = "E";
    }

    public Event(String input, Parser p) throws InvalidFormatException{
        super(null);
        Matcher m = pattern.matcher(input);
        if (!m.find()) {
            throw getInvalidFormatException();
        }
        this.description = m.group(1);
        this.to = LocalDate.parse(m.group(2), p.getInputFormat());
        this.from = LocalDate.parse(m.group(3), p.getInputFormat());
        classIcon = "E";
    }

    @Override
    public String toString() {
        return String.format(
                "%s From: %s To: %s", super.toString(), to, from);
    }

    @Override
    public String toString(Parser p) {
        return String.format("%s /from %s /to %s",
                super.toString(),
                p.convertDateToOutputFormat(to),
                p.convertDateToOutputFormat(from));
    }

    @Override
    public String toLog(Parser p) {
        return String.format("%s %s %s",
                super.toString(),
                p.convertDateToInputFormat(to),
                p.convertDateToInputFormat(from));
    }
}