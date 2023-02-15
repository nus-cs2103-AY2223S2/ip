package duke.task;

import java.time.format.DateTimeFormatter;

import duke.exception.DukeException;

/**
 * Represents a generic completable Task with a description.
 */
public abstract class Task {
    protected static final String CATEGORY_KEY = "category";
    protected static final String DESCRIPTION_KEY = "description";
    protected static final String COMPLETED_KEY = "completed";
    protected static final DateTimeFormatter RECEIVE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy kkmm");
    protected static final DateTimeFormatter PRINT_FORMAT = DateTimeFormatter.ofPattern("dd-MMM-uuuu,EEE,hh:mma");
    protected String description;
    protected boolean isCompleted;

    /**
     * Initialises a task.
     *
     * @param description Description of task.
     * @param isCompleted Whether the task has been completed.
     * @throws DukeException
     */
    public Task(String description, boolean isCompleted) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException("The description of a task cannot be empty");
        }
        this.description = description;
        this.isCompleted = isCompleted;
    }

    public abstract String serialize();

    public String getDescription() {
        return this.description;
    }

    public boolean isCompleted() {
        return this.isCompleted;
    }

    public void mark() {
        this.isCompleted = true;
    }

    public void unmark() {
        this.isCompleted = false;
    }

    public boolean matches(String pattern) {
        return description.contains(pattern);
    }

    public String getStatusIcon() {
        return isCompleted() ? "X" : " ";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getDescription());
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object instanceof Task) {
            Task t = (Task) object;
            return toString().equals(t.toString());
        } else {
            return false;
        }
    }
}
