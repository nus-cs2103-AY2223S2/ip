<<<<<<< HEAD
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
=======
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, String from, String to) {
        super(description);
        try {
            this.from = LocalDate.parse(from);
            this.to = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            throw DukeException.DATETIME_FORMAT;
        } catch (Exception e) {
            throw new DukeException("Unknown error occurred when parsing datetime.");
        }
>>>>>>> branch-Level-8
    }

    @Override
    public String toString() {
<<<<<<< HEAD
        return String.format("[E]%s (from: %s, to: %s)", super.toString(), this.from, this.to);
    }

    @Override
    public String toData() {
        return String.format("Event | description: %s ; from: %s ; to: %s", this.description, this.from, this.to);
    }

    public static Task fromData(String data) {
        Pattern pattern = Pattern.compile("(description:) (.*) ; (from:) (.*) ; (to:) (.*)");
        Matcher matcher = pattern.matcher(data);
        if (matcher.matches()) {
            String description = matcher.group(2);
            String from = matcher.group(4);
            String to = matcher.group(6);
            return new Event(description, from, to);
        }
        return Task.EMPTY_TASK;
    }
}
