package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * This class is an event task. It contains
 * the description of the task, the time the task
 * starts and the time the task ends.
 */
public class Event extends Task {

    private final LocalDate from;
    private final LocalDate to;

    /**
     * Constructs an event with the given description, from and to.
     *
     * @param description Description of the deadline task.
     * @param from The time the event task starts.
     * @param to The time the event task ends.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an event with the given description, isDone, from and to.
     *
     * @param description Description of the deadline task.
     * @param isDone Whether the deadline task is done.
     * @param from The time the event task starts.
     * @param to The time the event task ends.
     */
    public Event(String description, boolean isDone, LocalDate from, LocalDate to, ArrayList<String> tags) {
        super(description, isDone, tags);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("[E]%s  (from: %s to: %s) %s",
                super.toString(),
                this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                this.getStringOfTags());
    }

    @Override
    public String convertToStorableString() {
        return String.format("E|%s|%s|%s|%s|%s",
                this.isDone() ? "1" : "0",
                this.getDescription(),
                this.from.toString(),
                this.to.toString(),
                this.getTags()
        );
    }

}
