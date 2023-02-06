package pix.tasks;

import java.time.format.DateTimeFormatter;

/**
 * Parent task class containing description of task.
 */
public class Task {
    /** Description of task. */
    protected String description;

    /** Format for the input of dates. */
    protected DateTimeFormatter formatInput = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /** Format for the output of dates. */
    protected DateTimeFormatter formatOutput = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");

    /** Whether the task is done. */
    protected boolean isDone = false;

    /**
     * Constructs a new Task object.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return isDone ? "[X] " + description : "[ ] " + description;
    }

    /**
     * Format to save into duke.txt file locally.
     *
     * @return Formatted of tasks to save locally.
     */
    public String toSave() {
        return isDone ? " 1 / " + description : " 0 / " + description;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markUndone() {
        this.isDone = false;
    }

    public boolean inDescription(String keyword) {
        return this.description.contains(keyword);
    }
}
