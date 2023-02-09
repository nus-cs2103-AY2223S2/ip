package duke.task;

import java.io.Serializable;
import java.util.Objects;

import duke.exception.DukeIllegalArgumentException;
import duke.exception.DukeIllegalStateException;

/**
 * Abstract implementation of a {@code Task}.
 */
public abstract class Task implements Serializable {

    private static final long serialVersionUID = 4852600493024294334L;

    protected String description;
    protected boolean isDone;

    /**
     * Creates a {@code Task} with the given description. The created task will be marked as not
     * done.
     *
     * @param description a string describes the created task
     */
    public Task(String description) {
        if (description.isEmpty()) {
            throw new DukeIllegalArgumentException("description cannot be empty");
        }
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Marks this {@code Task} as done.
     */
    public void markAsDone() {
        if (isDone) {
            throw new DukeIllegalStateException("Task is already marked as done");
        }
        isDone = true;
    }

    /**
     * Marks this {@code Task} as not done.
     */
    public void unmarkAsDone() {
        if (!isDone) {
            throw new DukeIllegalStateException("Task is already marked as not done");
        }
        isDone = false;
    }

    public boolean contains(String keyword) {
        return description.contains(keyword);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ", description);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Task)) {
            return false;
        }
        Task task = (Task) obj;
        return Objects.equals(description, task.description) && isDone == task.isDone;
    }
}
