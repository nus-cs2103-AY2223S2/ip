package duke.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class Task implements Serializable {
    private final String taskDescription;
    private boolean isTaskDone;
    protected Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isTaskDone = false;
    }

    protected void markTaskDone() {
        this.isTaskDone = true;
    }

    protected void markTaskUndone() {
        this.isTaskDone = false;
    }

    @Override
    public String toString() {
        if (this.isTaskDone) {
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
