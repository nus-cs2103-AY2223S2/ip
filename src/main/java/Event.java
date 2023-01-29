import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    }

    public Event(String description, String from, String to, boolean isMarked) {
        this(description, from, to);
        this.isDone = isMarked;
    }

    @Override
    public String toString() {
        String formattedFrom = from.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String formattedTo = to.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return String.format("[E]%s (from: %s, to: %s)", super.toString(), formattedFrom, formattedTo);
    }

    @Override
    public String toData() {
        String formattedFrom = from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String formattedTo = to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return String.format("Event | marked: %s ; description: %s ; from: %s ; to: %s", this.isMarked(), this.description, formattedFrom, formattedTo);
    }

    public static Task fromData(String data) {
        Pattern pattern = Pattern.compile("(marked:) (.*) ; (description:) (.*) ; (from:) (.*) ; (to:) (.*)");
        Matcher matcher = pattern.matcher(data);
        if (matcher.matches()) {
            boolean isMarked = matcher.group(2).equals("1") ? true : false;
            String description = matcher.group(4);
            String from = matcher.group(6);
            String to = matcher.group(8);
            return new Event(description, from, to, isMarked);
        }
        return Task.EMPTY_TASK;
    }
}
