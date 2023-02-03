package duke.tasks;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import duke.dukeexceptions.DukeExceptions;
/**
 *  A task representing an event
 */
public class Event extends Task {
    private static final String tag = "E";
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     *  Constructor for an event.
     *
     * @param description the description of the event
     * @param from data and time of when event starts
     * @param to data and time of when event ends
     */
    public Event(String description, String from, String to) {
        super(description);
        try {
            String[] fromSplit = from.split(" ");
            LocalDate fromLocalDate = LocalDate.parse(fromSplit[0]);
            LocalTime fromLocalTime = LocalTime.parse(fromSplit[1]);
            this.from = LocalDateTime.of(fromLocalDate, fromLocalTime);

            String[] toSplit = to.split(" ");
            LocalDate toLocalDate = LocalDate.parse(toSplit[0]);
            LocalTime toLocalTime = LocalTime.parse(toSplit[1]);
            this.to = LocalDateTime.of(toLocalDate, toLocalTime);

        } catch (DateTimeParseException e) {
            throw new DukeExceptions("Format of date was not recognized, use YYYY-MM-DD and HH:MM");
        }
    }

    /**
     * Returns the event in a formatted string to be saved locally.
     *
     * @return the event in string format
     */
    public String saveTask() {
        String completed = this.isDone ? "1" : "0";
        return this.tag + " | " + completed + " | "
                + this.description
                + " | " + this.from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                + " - " + this.to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * Returns the event in formatted string to be printed into console.
     *
     * @return the event in string format
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + "(from:" + this.from.format(DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' hh:mm a"))
                + " to:" + this.to.format(DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' hh:mm a"))
                + ")" + "\n";
    }

    /**
     * Checks if an object is an event with the same description, from and to.
     *
     * @return boolean of whether an object is an event with the same description, from and to
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Event)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Event event = (Event) o;
        return Objects.equals(from, event.from) && Objects.equals(to, event.to);
    }

}
