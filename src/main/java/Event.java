/**
 * The event class that extends the Task class
 */
import java.time.LocalDate;

public class Event extends Task {
    protected String type;
    protected LocalDate startTime;
    protected LocalDate endTime;

    /**
     * The default constructor
     * @param description: the content of the command
     */
    public Event(String description) {
        super();
        int indexOfFrom = description.indexOf("/from");
        int indexOfTo = description.indexOf("/to");
        this.name = description.substring(0, indexOfFrom - " ".length());
        this.startTime = Task.parseDate(description.substring(indexOfFrom + "/from ".length(), indexOfTo - " ".length()));
        this.endTime = Task.parseDate(description.substring(description.indexOf("/to") + "/to ".length()));
        this.type = "E";
    }

    /**
     * Parse the start time of the event from the user string
     * @param s: the user-input string
     * @return the start time of the event
     */
    public static String parseStartTime(String s) {
        return s.substring(s.indexOf("/from") + "/from ".length(), s.indexOf("/to") - " ".length());
    }

    /**
     * Parse the end time of the event from the user string
     * @param s: the user-input string
     * @return the end time of the event
     */
    public static String parseEndTime(String s) {
        return s.substring(s.indexOf("/to"));
    }

    /**
     * Overriding the toString class
     * @return the string representation of an event
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (from: %s to: %s)", type,
                super.toString(), Task.printDate(startTime), Task.printDate(endTime));
    }
}
