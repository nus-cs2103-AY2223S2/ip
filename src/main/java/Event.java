import java.time.LocalDate;

/**
 * Encapsulation of an Event task,
 * a task that starts and ends at a specific date/time.
 */
public class Event extends Task {
    //Start time
    private LocalDate start;

    //End time
    private LocalDate end;

    /**
     * Constructor for Event.
     * @param name The name of the task.
     * @param start The start date/time of event.
     * @param end The end date/time of event.
     */
    public Event(String name, LocalDate start, LocalDate end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    /**
     * Constructor to instantiate an event.
     * @param name The name of the task.
     * @param start The start date/time of event.
     * @param end The end date/time of event.
     * @param isDone Status of the task.
     */
    public Event(String name, String start, String end, boolean isDone) {
        super(name, isDone);
        this.start = start;
        this.end = end;
    }

    /**
     * Get the string with a [E] icon representing this task.
     * @return A string representation of this Event task.
     */
    @Override
    public String toString() {
        String s = Duke.formatDate(this.start);
        String e = Duke.formatDate(this.end);
        String toPrint = String.format("[E]%s (from: %s to: %s)",
                super.toString(), s, e);
        return toPrint;
    }

    /**
     * Format task to be stored in data file.
     * @return Returns a  formatted string representation of this task to be stored.
     */
    @Override
    public String format() {
        return ("E | " + super.format() + String.format(" | %s | %s", this.start, this.end));
    }
}