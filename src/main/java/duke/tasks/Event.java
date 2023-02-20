package duke.tasks;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.ocpsoft.prettytime.nlp.PrettyTimeParser;

/**
 * Represents an event.
 *
 * @author Samarth Verma
 */
public class Event extends Task {

    private String desc;
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Creates a new Event.
     *
     * @param id          The id of the event.
     * @param description The description of the event.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public Event(int id, String description, String from, String to) {
        super(id);

        List<Date> fromDates = new PrettyTimeParser().parse(from);
        List<Date> toDates = new PrettyTimeParser().parse(to);

        desc = description;
        
        if(fromDates.size() == 0 || toDates.size() == 0) {
            throw new IllegalArgumentException("Unable to parse 'from' or 'to' date.");
        } else {
            this.from = convertDateToLocalDateTime(fromDates.get(0));
            this.to = convertDateToLocalDateTime(toDates.get(0));
        }
    }

    private LocalDateTime convertDateToLocalDateTime(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    @Override
    public String description() {
        return desc;
    }

    @Override
    public String toString() {
        String isDone = isCompleted() ? "X" : " ";
        return String.format("[E][%s] %s (from: %s to %s)", isDone, description(),
                from.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm a")),
                to.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm a")));
    }

    @Override
    public String serialize() {
        String isDone = isCompleted() ? "1" : "0";
        return String.format("E|%s|%s|%s|%s|%s", id(), isDone, description(), from, to);
    }

    /**
     * Deserializes an event from a string.
     *
     * @param s The string to deserialize from.
     * @return The deserialized event.
     */
    public static Event deserialize(String s) {
        String[] parts = s.split("\\|");
        Event event = new Event(Integer.parseInt(parts[1]), parts[3], parts[4], parts[5]);
        if (parts[2].equals("1")) {
            event.markCompleted();
        }
        return event;
    }
}
