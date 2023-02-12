package duke.task;

import duke.tag.Tag;

import java.time.LocalDateTime;

/**
 * An Event which is a type of Task.
 */
public class Event extends Task {
    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Constructor for Event.
     *
     * @param description Task description.
     * @param start Starting time of event.
     * @param end Ending time of event.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public Event(String description, LocalDateTime start, LocalDateTime end, Tag tag) {
        super(description, tag);
        this.start = start;
        this.end = end;
    }

    /**
     * Constructor for Event.
     *
     * @param description Task description.
     * @param isDone Completed status of task.
     * @param start Starting time of event.
     * @param end Ending time of event.
     */
    public Event(String description, boolean isDone, LocalDateTime start, LocalDateTime end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    public Event(String description, boolean isDone, LocalDateTime start, LocalDateTime end, Tag tag) {
        super(description, isDone, tag);
        this.start = start;
        this.end = end;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getSaveTaskString() {
        return String.format("E | %s | from: %s | to: %s%s", super.toString(), formatSavedDateTime(start),
                formatSavedDateTime(end), getTagString());
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return String.format("E | %s | from: %s to: %s%s", super.toString(), displayDateTime(start),
                displayDateTime(end), getTagString());
    }
}
