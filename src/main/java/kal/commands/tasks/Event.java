package kal.commands.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class handles Events and their related operations.
 */
public class Event extends Task {
    private static final String IDENTIFIER = "E";
    private static final String DATE_FORMAT = "MMM dd yyyy";
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructs an Event object.
     *
     * @param description The description of the Event.
     * @param from The start time of the Event.
     * @param to The end time of the Event.
     */
    public Event(String description, String from, String to) {
        this(description, false, from, to);
    }

    /**
     * Constructs an Event object.
     *
     * @param description The description of the Event.
     * @param isDone The completion status of the Event.
     * @param from The start time of the Event.
     * @param to The end time of the Event.
     */
    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    /**
     * Generates a letter representing the type of task.
     *
     * @return a letter representing the type of this task.
     */
    public String getTaskClass() {
        return Event.IDENTIFIER;
    }

    /**
     * Generates a String to store this task in a local text file.
     *
     * @return A representative String that contains data about the current task.
     */
    public String generateStorageText() {
        return String.format("%s~%s~%s~%s~%s",
                this.getTaskClass(), this.getStatusIcon(),
                this.getDescription(), this.from.toString(), this.to.toString());
    }

    private String getFrom() {
        return this.from.format(DateTimeFormatter.ofPattern(Event.DATE_FORMAT));
    }

    private String getTo() {
        return this.to.format(DateTimeFormatter.ofPattern(Event.DATE_FORMAT));
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (from: %s to: %s)",
                this.getTaskClass(), this.getStatusIcon(),
                this.description, this.getFrom(), this.getTo());
    }
}
