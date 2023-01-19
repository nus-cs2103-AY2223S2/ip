package jarvis.task;

import jarvis.exception.CommandParseException;
import jarvis.exception.MissingParameterException;

import java.util.Scanner;

public abstract class Task {

    /**
     * Serializes a task for storage.
     * @return Serial string.
     */
    public abstract String serialize();

    private boolean isDone;
    private final String description;

    /**
     * Constructor for a task, marked as undone.
     * @param description Description of the task.
     * @throws MissingParameterException If description is null or blank.
     */
    public Task(String description) throws MissingParameterException {
        if (description == null || description.isBlank()) {
            throw new MissingParameterException("Missing description", "A task description is needed.");
        }

        this.isDone = false;
        this.description = description;
    }

    /**
     * Constructor for a task.
     * @param description Description of the task.
     * @param isDone Whether the task is marked as done.
     * @throws MissingParameterException If description is null or blank.
     */
    public Task(String description, boolean isDone) throws MissingParameterException {
        this(description);
        this.isDone = isDone;
    }

    /**
     * Getter of isDone.
     * @return Whether the task has been marked as done.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Setter of isDone.
     * @param isDone Whether the task is to be marked as done.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Getter of description.
     * @return Task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns whether the task satisfies the given filter.
     * @param filter Task filter.
     * @return Whether the task satisfies the given filter.
     */
    public boolean satisfies(TaskFilter filter) {
        return filter == null || filter.isEmpty();
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.isDone ? "X" : " ", this.description);
    }
}
