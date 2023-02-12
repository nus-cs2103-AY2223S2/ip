package seedu.duke.tasks;

import seedu.duke.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructor for Event
     *
     * @param description String description of the task
     * @param from /from timestamp of the task in LocalDateTime
     * @param to /to timestamp of the task in LocalDateTime
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        this(description, false, "E", from, to);
    }

    /**
     * Constructor for Storage's creation of Deadline when reading save file
     *
     * @param description String description of the task
     * @param isDone whether the task has been marked
     * @param taskType String representing the type of task
     * @param from /from timestamp of the task in LocalDateTime
     * @param to /to timestamp of the task in LocalDateTime
     */
    public Event(String description, boolean isDone, String taskType, LocalDateTime from, LocalDateTime to) {
        super(description, isDone, taskType);
        this.from = from;
        this.to = to;
    }

    /**
     * {@inheritDoc}
     */
    public Task markTask() throws DukeException {
        if (super.isDone) {
            throw new DukeException("This task is already marked!");
        }
        return new Event(super.description, true, super.taskType, this.from, this.to);
    }

    /**
     * {@inheritDoc}
     */
    public Task unmarkTask() throws DukeException {
        if (!super.isDone) {
            throw new DukeException("This task is already unmarked!");
        }
        return new Event(super.description, false, super.taskType, this.from, this.to);
    }

    /**
     * {@inheritDoc}
     */
    public String formatTask() {
        return String.format("E|%b|%s|%s|%s", this.isDone, this.description, this.from.toString(), this.to.toString());
    }

    @Override
    public String toString() {
        String timePattern = "d MMM yyyy, HHmm";
        return String.format("%s%s %s (From: %s To: %s)", super.getTaskTypeBox(), super.getStatusCheckbox(),
                super.toString(), this.from.format(DateTimeFormatter.ofPattern(timePattern)),
                this.to.format(DateTimeFormatter.ofPattern(timePattern)));
    }
}
