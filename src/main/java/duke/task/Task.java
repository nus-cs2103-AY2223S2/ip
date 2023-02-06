package duke.task;

import duke.TaskCreationException;

import java.io.Serializable;

/**
 * Represents a task
 */
public abstract class Task implements Serializable {
    /**
     * Priority of task
     */
    public enum Priority {
        LOW,
        MEDIUM,
        HIGH
    }


    public final String desc;
    protected boolean isDone;

    private Priority priority;

    Task(String desc) throws TaskCreationException {
        if (desc.equals("")) {
            throw new TaskCreationException("Description of todo cannot be empty");
        }
        this.desc = desc;
        this.isDone = false;
        this.priority = Priority.LOW;
    }

    @Override
    public String toString() {
        return String.format("%s%s[%s] %s", getStatusIcon(), getType(), priority, desc);
    }

    protected String getStatusIcon() {
        return this.isDone ? "[X]" : "[]";
    }

    protected abstract String getType();

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }
}
