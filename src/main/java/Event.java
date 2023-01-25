import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Event extends Task{
    protected LocalDateTime from;
    protected LocalDateTime to;
    protected boolean fromHasTime;
    protected boolean toHasTime;

    public Event(String description, LocalDateTime from, LocalDateTime to, boolean fromHasTime, boolean toHasTime) {
        super(description);
        this.from = from;
        this.to = to;
        this.fromHasTime = fromHasTime;
        this.toHasTime = toHasTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.getFrom() + " to: " + this.getTo() + ")";
    }

    public String getFrom() {
        if (fromHasTime) {
            return DateTimeParser.dateTimeToDisplayString(this.from);
        } else {
            return DateTimeParser.dateToDisplayString(this.from.toLocalDate());
        }
    }

    public String getTo() {
        if (toHasTime) {
            return DateTimeParser.dateTimeToDisplayString(this.to);
        } else {
            return DateTimeParser.dateToDisplayString(this.to.toLocalDate());
        }
    }

    public String getToInStorageFormat() {
        if (toHasTime) {
            return DateTimeParser.dateTimeToStorageString(this.to);
        } else {
            return DateTimeParser.dateToStorageString(this.to.toLocalDate());
        }
    }

    public String getFromInStorageFormat() {
        if (fromHasTime) {
            return DateTimeParser.dateTimeToStorageString(this.from);
        } else {
            return DateTimeParser.dateToStorageString(this.from.toLocalDate());
        }
    }

    public static Event parseEventCommand(Scanner stringStream) throws DukeException, DateTimeParseException {
        String taskDesc = "";
        String fromString = "";
        String toString = "";

        boolean foundFrom = false;
        boolean foundTo = false;

        while (stringStream.hasNext()) {
            String temp = stringStream.next();

            if (temp.equalsIgnoreCase("/from")) {
                foundFrom = true;
                continue;
            } else if (temp.equalsIgnoreCase("/to")) {
                foundTo = true;
                continue;
            }

            if (foundTo) {
                toString += temp + " ";
            } else if (foundFrom) {
                fromString += temp + " ";
            } else {
                taskDesc += temp + " ";
            }
        }

        if (taskDesc.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }

        if (!foundFrom || !foundTo || fromString.isEmpty() || toString.isEmpty()) {
            throw new DukeException("☹ OOPS!!! Event tasks require a /from and /to.");
        }

        fromString = fromString.trim();
        toString = toString.trim();
        LocalDateTime from = DateTimeParser.parse(fromString);
        LocalDateTime to = DateTimeParser.parse(toString);

        String[] fromParts = fromString.split(" ");
        boolean fromHasTime = fromParts.length == 2;

        String[] toParts = toString.split(" ");
        boolean toHasTime = toParts.length == 2;

        Event newTask = new Event(taskDesc.trim(), from, to, fromHasTime, toHasTime);
        return newTask;
    }

    public static Event parseEventStringArray(String[] parts) throws DateTimeParseException {
        String taskDesc = parts[2];
        String fromString = parts[3];
        String toString = parts[4];

        String[] fromParts = fromString.split(" ");
        boolean fromHasTime = fromParts.length == 2;

        String[] toParts = toString.split(" ");
        boolean toHasTime = toParts.length == 2;

        Event task = new Event(taskDesc,
                DateTimeParser.parse(fromString),
                DateTimeParser.parse(toString),
                fromHasTime,
                toHasTime);

        if (Integer.parseInt(parts[1]) == 1) {
            task.mark();
        }
        return task;
    }
}
