package duke.task;

import java.io.Serializable;

/**
 * Abstract class from which all other tasks are derived
 */
public abstract class Task implements Serializable {
    /**
     * String label for the task
     */
    private final String description;

    private final String typeString;

    /**
     * Boolean indicating if the task is completed or otherwise
     */
    private boolean done;

    public Task(String task) {
        this.description = task;
        TaskInfo anno = getClass().getAnnotation(TaskInfo.class);
        this.typeString = anno.type();
    }

    /**
     * Set whether the task is done or not
     * @param value True if it is done, false otherwise
     */
    public void setDone(boolean value) {
        this.done = value;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Get the type string for the task
     * @return String
     */
    public String getType() {
        return typeString;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", getType(), this.done ? "X" : " ", this.description);
    }
}
