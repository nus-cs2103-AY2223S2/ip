package duke.model.task;

import java.io.Serializable;
import java.util.Objects;

import duke.common.exception.DukeIllegalArgumentException;
import duke.common.exception.DukeIllegalStateException;

/**
 * Abstract implementation of a {@code Task}.
 */
public abstract class Task implements Serializable {

    private static final long serialVersionUID = 4852600493024294334L;

    /**
     * The description of this task.
     */
    protected String description;

    /**
     * The current status of this task.
     */
    protected boolean isDone;

    /**
     * Creates a {@code Task} with the given description. The created task will be marked as not
     * done.
     *
     * @param description a string describes the created task
     * @throws DukeIllegalArgumentException if the description is empty
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
        if (description.isEmpty()) {
            throw new DukeIllegalArgumentException("description cannot be empty");
        }
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

    /**
     * Returns whether this tasks is done or not.
     *
     * @return {@code true} if this task is done, otherwise {@code false}
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Checks whether this task is the same as another task, based on the runtime classes and the
     * descriptions.
     *
     * @param that the other task
     * @return {@code true} if this task is the same as the other task, otherwise {@code false}
     */
    public boolean isSameTask(Task that) {
        Class<?> thisClazz = this.getClass();
        Class<?> thatClazz = that.getClass();
        return thisClazz.equals(thatClazz) && Objects.equals(description, that.description);
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
        return isDone == task.isDone && Objects.equals(description, task.description);
    }
}
