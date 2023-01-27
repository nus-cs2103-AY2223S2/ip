package seedu.duke.tasks;

import seedu.duke.DukeException;

public class Todo extends Task {

    /**
     * Constructor for Deadline
     *
     * @param description String description of the task
     */
    public Todo(String description) {
        this(description, false, "T");
    }

    /**
     * Constructor for Storage's creation of Deadline when reading save file
     *
     * @param description String description of the task
     * @param isDone whether the task has been marked
     * @param taskType String representing the type of task
     */
    public Todo(String description, boolean isDone, String taskType) {
        super(description, isDone, taskType);
    }

    /**
     * {@inheritDoc}
     */
    public Task markTask() throws DukeException {
        if (super.isDone) {
            throw new DukeException("This task is already marked!");
        }
        return new Todo(super.description, true, super.taskType);
    }

    /**
     * {@inheritDoc}
     */
    public Task unmarkTask() throws DukeException {
        if (!super.isDone) {
            throw new DukeException("This task is already unmarked!");
        }
        return new Todo(this.description, false, super.taskType);
    }

    /**
     * {@inheritDoc}
     */
    public String formatTask() {
        return String.format("T|%b|%s", this.isDone, this.description);
    }

    @Override
    public String toString() {
        return String.format("%s%s %s", super.getTaskTypeBox(), super.getStatusCheckbox(), super.toString());
    }
}
