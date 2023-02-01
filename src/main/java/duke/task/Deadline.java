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

    public Deadline(String description, String preBy, Parser p) {
        super(description);
        this.by = LocalDate.parse(preBy, p.getInputFormat());
        classIcon = "D";
    }

    public Deadline(String input, Parser p) throws InvalidFormatException{
        super(null);
        Matcher m = pattern.matcher(input);
        if (!m.find()) {
            throw getInvalidFormatException();
        }
        this.description = m.group(1);
        this.by = LocalDate.parse(m.group(2), p.getInputFormat());
        classIcon = "D";
    }

    @Override
    public String toString() {
        return String.format("%s By: %s", super.toString(), by);
    }

    @Override
    public String toString(Parser p) {
        return String.format("%s By: %s", super.toString(), p.convertDateToOutputFormat(by));
    }

    @Override
    public String toLog(Parser p) {
        return String.format("%s %s", super.toString(), p.convertDateToInputFormat(by));
    }

}