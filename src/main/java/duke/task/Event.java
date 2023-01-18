package duke.task;

import java.time.LocalDate;

/**
 * A task implementation with a start and end date.
 */
public class Event extends Task {

    /**
     * The start date of the task.
     */
    private final LocalDate from;

    /**
     * The end date of the task.
     */
    private final LocalDate to;

    /**
     * Constructs a new Event with the given description and start and end dates.
     * @param desc the description of the task
     * @param from the start date of the task
     * @param to the end date of the task
     */
    public Event(String desc, String from, String to) {
        super(desc);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s) (to: %s)", super.toString(),
                from.format(DATE_FORMAT),
                to.format(DATE_FORMAT));
    }
}
