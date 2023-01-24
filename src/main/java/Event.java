import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.getStatusIcon() + " "
                + super.getDescription() + "(from: " + this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + "to: " + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    public static Event addEvent(String details) {
        String description = details.substring(0, details.indexOf("/from"));
        String fromAndTo = details.substring(details.indexOf("/from") + "/from".length() + 1);
        String fromString = fromAndTo.substring(0, fromAndTo.indexOf("/to"));
        String toString = fromAndTo.substring(fromAndTo.indexOf("/to") + "/to".length() + 1);

        String[] possibleFormats = {"yyyy-mm-dd", "dd/MM/yyyy HHmm", "d/MM/yyyy HHmm", "MMM dd yyyy", "MMMM dd yyyy"};
        LocalDateTime from = null;
        LocalDateTime to = null;
        for (int i = 0; i < possibleFormats.length; i++) {
            try {
                from = LocalDateTime.parse(fromString, DateTimeFormatter.ofPattern(possibleFormats[i]));
            } catch (DateTimeParseException ignored) {
            }
        }

        for (int i = 0; i < possibleFormats.length; i++) {
            try {
                to = LocalDateTime.parse(toString, DateTimeFormatter.ofPattern(possibleFormats[i]));
            } catch (DateTimeParseException ignored) {
            }
        }

        Event t = new Event(description, from, to);
        System.out.println("Got it. I've added this task:");
        System.out.println(t);
        return t;
    }
}