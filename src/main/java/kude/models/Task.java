package kude.models;

import java.io.Serializable;

/**
 * Base class for all tasks
 */
public abstract class Task implements Serializable {
    private final String content;
    private boolean isDone;

    /**
     * Initialize this task with the provided content
     */
    public Task(String content) {
        this.content = content;
        this.isDone = false;
    }

    /**
     * Gets the content of this task
     */
    public String getContent() {
        return content;
    }

    /**
     * Get whether this task is done
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Sets whether this task is done
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        var icon = isDone ? "X" : " ";
        return String.format("[%s] %s", icon, content);
    }
}
