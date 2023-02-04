package duke.task;

import duke.util.TaskList;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that is managed by Duke.
 * A task has a description and can either be a {@link ToDo}; a {@link Deadline}
 * with a due date or a {@link Event} with a start and end date.
 *
 * @see duke.Duke
 * @see TaskList
 */
public class Task implements Serializable {

    static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("eee, d MMM uuuu");
    /**
     * The description of the task.
     */
    private final String desc;
    /**
     * The status of the task.
     */
    private boolean isDone;

    /**
     * Constructs a new task with the given description.
     *
     * @param desc the description of the task
     */
    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Returns the status of the task.
     *
     * @return True if the task is marked as done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Toggles the status of the task.
     */
    public void mark() {
        this.mark(!this.isDone);
    }

    /**
     * Explicitly marks the task as done (or not done).
     *
     * @param isDone The new status of the task.
     */
    public void mark(boolean isDone){
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + desc;
    }

}
