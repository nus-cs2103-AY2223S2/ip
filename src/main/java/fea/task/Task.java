package fea.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Task class that represents a task.
 */

public class Task {
    private String description;
    private boolean isMark;
    private LocalDateTime reminder;

    /**
     * Constructor method to initialise Task.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isMark = false;
    }

    public String getDescription() {
        return description;
    }

    public Character getMark() {
        return isMark ? 'X' : ' ';
    }

    public void setReminder(LocalDateTime reminder) {
        this.reminder = reminder;
    }

    public LocalDateTime getReminder() {
        return reminder;
    }

    /**
     * Toggles the mark of the task.
     */
    public void toggleMark() {
        this.isMark = !this.isMark;
    }
    /**
     * Returns the details of the task in a string format.
     * @return String The string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%c]%s %s", getMark(), printReminder(), this.description);
    }

    protected String parseDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma");
        return dateTime.format(formatter);
    }

    private String printReminder() {
        if (this.reminder != null) {
            return String.format(" (Reminder: %s)" , parseDateTime(this.reminder));
        }
        return "";
    }
}
