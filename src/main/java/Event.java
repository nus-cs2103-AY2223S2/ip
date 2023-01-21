import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Event class extends the DatedTask class and represents an event with a start and end date.
 */
public class Event extends DatedTask {
    private static final long serialVersionUID = 103;

    private LocalDate start;
    private LocalDate end;

    /**
     * Constructs an Event task with a start and end date.
     *
     * @param name  The name of the event.
     * @param start The date the event begins on.
     * @param end   The date the event ends on.
     * @throws DukeException If the dates are not formatted properly.
     */
    public Event(String name, String start, String end) throws DukeException {
        super(name, LocalDate.parse(end));
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
    }

    @Override
    public boolean hasDate() {
        return true;
    }

    /**
     * Returns the string representation of the event.
     *
     * @return The string representation of the event.
     */
    @Override
    public String toString() {
        DateTimeFormatter daydm = DateTimeFormatter.ofPattern("E, dd MMM");
        DateTimeFormatter daydmyy = DateTimeFormatter.ofPattern("E, dd MMM uu");
        return "[E]" + super.toString() + " (from: " + start.format(daydm) + " to: " + end.format(daydmyy)
                + ")";
    }
}
