package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

public class Task {
    private final String title;
    private Boolean isDone;

    public Task(String title) {
        this.title = title;
        this.isDone = false;
    }

    /**
     * Mark the task as done.
     */
    public void tick() {
        this.isDone = true;
    }

    /**
     * Mark the task as not done.
     */
    public void untick() {
        this.isDone = false;
    }

    /**
     * Return the display string representing the task.
     * @return string form of the deadline
     */
    @Override
    public String toString() {
        String checkBox = this.isDone ? "[X]" : "[ ]";
        return checkBox + " " + this.title;
    }

    /**
     * Return the saved string representing the task.
     * @return saved string form of the deadline
     *
     */
    public String toSavedString() {
        return (this.isDone ? "1" : "0") + "|" + this.title;
    }

    public String getTitle() {
        return this.title;
    }
}

