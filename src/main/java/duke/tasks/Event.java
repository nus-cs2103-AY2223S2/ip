package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/** A type of Task that contains a description, a start time and an end time
 */
public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    /** Constructor for the Event class
     *
     * @param description description of the Event
     * @param from start time of Event
     * @param to end time of Event
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /** Another constructor for the Event class
     *
     * @param description description of the Event
     * @param from start time of Event
     * @param to end time of Event
     * @param isDone whether the task should be marked upon creation
     */
    public Event(String description, LocalDate from, LocalDate to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /** Returns the string representation of Event
     *
     * @return string representation of Event class
     */
    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + " (from: "
                + this.from.format(DateTimeFormatter.ofPattern("d MMM yyyy"))
                + " to: "
                + this.to.format(DateTimeFormatter.ofPattern("d MMM yyyy"))
                + ")";
    }

    /** Returns the string representation of Event in data format
     *
     * @return string representation of Event class in data format
     */
    @Override
    public String toDataFormatString() {
        int marked = super.isDone ? 1 : 0;
        return "E / "
                + marked
                + " / "
                + super.description
                + " - "
                + this.from.format(DateTimeFormatter.ofPattern("d/MM/yyyy"))
                + " - "
                + this.to.format(DateTimeFormatter.ofPattern("d/MM/yyyy"));
    }

    /**
     * Checks if the given Object is the same as this
     *
     * @param o the Object being compared
     * @return true if o is an instance of this and have the same description, from and to
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Event)) {
            return false;
        }

        Event e = (Event) o;
        return this.description.equals(e.description) && this.from.equals(e.from) && this.to.equals(e.to);
    }
}
