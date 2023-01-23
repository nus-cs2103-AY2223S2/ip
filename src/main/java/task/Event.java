package task;

import java.time.LocalDate;
import ui.Parser;
import duke.DukeException;

/**
 * The event class that extends the Task class.
 * An even should have a start and an end date.
 */
public class Event extends Task {
    protected LocalDate startTime;
    protected LocalDate endTime;

    /**
     * The default constructor
     *
     * @param description: the content of the command
     */
    public Event(String description) throws DukeException {
        super();
        int indexOfFrom = description.indexOf("/from");
        int indexOfTo = description.indexOf("/to");
        this.name = description.substring(0, indexOfFrom - " ".length());
        this.startTime = Parser.parseDate(
                description.substring(indexOfFrom + "/from ".length(),
                indexOfTo - " ".length()));
        this.endTime = Parser.parseDate(
                description.substring(
                        description.indexOf("/to") + "/to ".length()));
        this.type = "E";
    }

    /**
     * Parse the start time of the event from the user string
     *
     * @param s: the user-input string
     * @return the start time of the event
     */
    public static String parseStartTime(String s) {
        return s.substring(s.indexOf("/from") + "/from ".length(),
                s.indexOf("/to") - " ".length());
    }

    /**
     * Parse the end time of the event from the user string
     *
     * @param s: the user-input string
     * @return the end time of the event
     */
    public static String parseEndTime(String s) {
        return s.substring(s.indexOf("/to"));
    }

    /**
     * Overriding the toString class
     *
     * @return the string representation of an event
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (from: %s to: %s)", type,
                super.toString(), Task.printDate(startTime), Task.printDate(endTime));
    }
}
