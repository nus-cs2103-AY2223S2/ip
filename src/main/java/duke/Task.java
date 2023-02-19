package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task. A Task object corresponds to a task that represented by
 * its completion status(done),description and deadline
 */

public class Task {
    private boolean isDone;
    private String description;
    private LocalDate deadline;

    /**
     * Initialises an task object with the given description
     * 
     * @param description
     *            description of task
     */

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.deadline = null;
    }

    /**
     * Initialises an task object with the given description and deadline
     * 
     * @param description description of task
     * @param deadline deadline of task
     */

    public Task(String description, LocalDate deadline) {
        this.description = description;
        this.isDone = false;
        this.deadline = deadline;
    }

    public String mark() {
        this.isDone = true;
        return this.toString();
    }

    public String unMark() {
        this.isDone = false;
        return this.toString();
    }

    public Boolean shouldContains(String searchString) {
        return this.description.contains(searchString);
    }

    public Boolean isUpcomingDeadline() {
        if (this.deadline == null) {
            return false;
        }

        if (this.deadline.isBefore(LocalDate.now())) {
            return false;
        }

        return true;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String doneString;
        if (isDone) {
            doneString = "[X]";
        } else {
            doneString = "[ ]";
        }
        String dateString;
        if (this.deadline == null) {
            dateString = "";
        } else {
            dateString = " by " + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }

        return doneString + " " + description + dateString;
    }

    /**
     * Returns the String representation of Task that will be stored in hard disk
     * @return String Representation of Task in format to be saved in hard disk
     * 
     */
    public String getTaskFileSaveFormat() {
        String doneString;
        if (isDone) {
            doneString = "[X]";
        } else {
            doneString = "[ ]";
        }

        String dateString;

        if (this.deadline == null) {
            dateString = "";
        } else {
            dateString = "###" + this.deadline.toString();
        }

        return doneString + "###" + description + dateString;
    }
}
