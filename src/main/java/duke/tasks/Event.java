package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The class representing an Event task.
 */
public class Event extends Task {
    /** Format of DateTime accepted to create an Event object.  */
    private static final DateTimeFormatter IN_FORMAT = DateTimeFormatter
                                                        .ofPattern("dd/MM/yyyy HHmm");
    /** User friendly format of DateTime which is displayed to the user.  */
    private static final DateTimeFormatter OUT_FORMAT = DateTimeFormatter
                                                        .ofPattern("dd LLL, h:mma");
    private static final String FORMAT = "event {task name}"
                                        + "/from {dd/mm/yyyy HHmm}"
                                        + "/to {dd/mm/yyyy HHmm}";
    private LocalDateTime to;
    private LocalDateTime from;
    private final boolean hasDate = true;

    /**
     * The constructor that initialises an Event task.
     * Event task is created with a given description, start time and end time.
     * @param desc Description of the Event task.
     * @param from Start DateTime of the Event task.
     * @param to End DateTime of the Event task.
     */
    public Event(String desc, String from, String to) {
        super(desc);
        setFrom(from);
        setTo(to);
    }

    private void setFrom(String from) {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(from, IN_FORMAT);
            this.from = dateTime;
        } catch (DateTimeParseException d) {
            System.out.println("Invalid date/time format for Deadline.");
        }
    }

    private void setTo(String to) {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(to, IN_FORMAT);
            this.to = dateTime;
        } catch (DateTimeParseException d) {
            System.out.println("Invalid date/time format for Deadline.");
        }
    }

    private String duration() {
        return " (from: " + this.from.format(OUT_FORMAT) + ", to: "
                + this.to.format(OUT_FORMAT) + ")";
    }

    /**
     * User friendly guide to help users in case of InvalidCommandException.
     * @return String representing the format of Event Task.
     */
    public static String showFomat() {
        return "Create an `Event` with: " + FORMAT;
    }

    public LocalDateTime getDate() {
        return from;
    }

    /**
     * User friendly interpretation of Event task object.
     * Displays Task type, description, and duration.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + this.duration();
    }
}
