package seedu.duke.tasks;

import seedu.duke.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime deadline;

    /**
     * Constructor for Deadline
     *
     * @param description String description of the task
     * @param deadline deadline of the task in LocalDateTime
     */
    public Deadline(String description, LocalDateTime deadline) {
        this(description, false, "D", deadline);
    }

    /**
     * Constructor for Storage's creation of Deadline when reading save file
     *
     * @param description String description of the task
     * @param isDone whether the task has been marked
     * @param taskType String representing the type of task
     * @param deadline deadline of the task in LocalDateTime
     */
    public Deadline(String description, boolean isDone, String taskType, LocalDateTime deadline) {
        super(description, isDone, taskType);
        this.deadline = deadline;
    }

    /**
     * {@inheritDoc}
     */
    public Task markTask() throws DukeException {
        if (super.isDone) {
            throw new DukeException("This task is already marked!");
        }
        return new Deadline(super.description, true, super.taskType, this.deadline);
    }

    /**
     * {@inheritDoc}
     */
    public Task unmarkTask() throws DukeException {
        if (!super.isDone) {
            throw new DukeException("This task is already unmarked!");
        }
        return new Deadline(super.description, false, super.taskType, this.deadline);
    }

    /**
     * {@inheritDoc}
     */
    public String formatTask() {
        return String.format("D|%b|%s|%s", this.isDone, this.description, this.deadline.toString());
    }

    @Override
    public String toString() {
        String timePattern = "d MMM yyyy, HHmm";
        return String.format("%s%s %s (By: %s)", super.getTaskTypeBox(), super.getStatusCheckbox(),
                super.toString(), this.deadline.format(DateTimeFormatter.ofPattern(timePattern)));
    }
}