package duke.model;

import java.time.LocalDateTime;

public abstract class Task {
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

    public String getTaskDescription() {
        return this.taskDescription;
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

}
