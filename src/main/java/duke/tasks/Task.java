package duke.tasks;

import java.time.LocalDate;

import duke.exceptions.DukeException;

/**
 * A class for task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for task.
     * @param description the description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markTask() {
        this.isDone = true;
    }

    public void unmarkTask() {
        this.isDone = false;
    }

    public boolean marked() {
        return this.isDone;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDeadline() {
        return false;
    }

    public boolean isEvent() {
        return false;
    }

    public LocalDate getDeadline() throws DukeException {
        throw new DukeException("Not a deadline");
    }

    public boolean isBetween(LocalDate date) throws DukeException {
        throw new DukeException("Not an event");
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}
