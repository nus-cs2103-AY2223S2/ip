package duke.task;

import java.io.Serializable;

/**
 * Abstract class from which all other tasks are derived
 */
public abstract class Task implements Serializable {
    public static int DEFAULT_PRIORITY = 0;

    /**
     * String label for the task
     */
    private final String description;

    private final String typeString;

    /**
     * Boolean indicating if the task is completed or otherwise
     */
    private boolean isDone;

    private final int priority;

    public Task(String task, int priority) {
        this.description = task;
        this.priority = priority;
        TaskInfo anno = getClass().getAnnotation(TaskInfo.class);
        this.typeString = anno.type();
    }

    public Task(String task) {
        this(task, DEFAULT_PRIORITY);
    }

    /**
     * Set whether the task is done or not
     * @param value True if it is done, false otherwise
     */
    public void setDone(boolean value) {
        this.isDone = value;
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
        return String.format("[%s][%s][%d] %s", 
            getType(), 
            this.isDone ? "X" : " ", 
            this.priority,
            this.description
        );
    }
}
