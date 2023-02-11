package duke.model;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * The `Task` class is a representation of a single task that can be either a `Deadline`, `Event` or `Todo`.
 * It includes information such as task description, and whether it is completed or not.
 *
 * @author jayanth
 */
public abstract class Task implements Serializable {
    private final String taskDescription;
    private boolean taskDone;
    protected Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.taskDone = false;
    }

    protected void markTaskDone() {
        this.taskDone = true;
    }

    protected void markTaskUndone() {
        this.taskDone = false;
    }

    @Override
    public String toString() {
        if (this.taskDone) {
            return "[X] " + this.taskDescription;
        } else {
            return "[ ] " + this.taskDescription;
        }
    }

    boolean isDueOn(LocalDateTime time) {
        return false; // general tasks don't have a specific deadline
    }

    boolean descriptionContains(String s) {
        return this.taskDescription.contains(s);
    }
}
