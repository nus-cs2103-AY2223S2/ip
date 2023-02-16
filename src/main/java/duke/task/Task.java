package duke.task;

import duke.Priority;

public class Task {
    protected String description;
    protected boolean isDone;
    protected Priority priority;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        // priority is set to medium by default
        this.priority = priority.MEDIUM;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public void setPriority(Priority priorityLevel) {
        this.priority = priorityLevel;
    }

    public void setPriority(String priorityLevel) {
        this.priority = Priority.parsePriority(priorityLevel);
    }
}
