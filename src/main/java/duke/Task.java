package duke;

import java.time.LocalDate;

/**
 * A task that contains the description and
 * whether it is marked done or not.
 */
abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs task with description of task.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns marked task.
     *
     * @return Marked task.
     */
    public Task mark() {
        this.isDone = !this.isDone;
        return this;
    }

    /**
     * Returns if task is marked done or not.
     *
     * @return Task done or not done, true or false.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns task status icon.
     *
     * @return Task status icon, X or blank.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return this.description;
    }

    public int containsDate() {
        return 0;
    }

    abstract LocalDate getDate();

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] "
                + description;
    }
}
