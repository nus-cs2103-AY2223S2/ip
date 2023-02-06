package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * class for tasks with a deadline
 */
public class Deadline extends Task {
    private static final String taskType = "[D]";
    private LocalDate date;

    /**
     * constructor for new Deadline instance
     * 
     * @param description description of task
     * @param date        date of the deadline
     * @throws MissingDescriptionException
     */
    public Deadline(String description, LocalDate date) throws MissingDescriptionException {
        super(description);
        this.date = date;
    }

    /**
     * returns string representation of tasks
     * 
     * @return string representation of tasks with type of task, completion status,
     *         task description, and date
     */
    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d MMM u");
        String dateString = date.format(dateFormatter);
        return taskType + super.toString() + " (by: " + dateString + ")";
    }

    /**
     * returns the string representation of tasks to be saved into text file
     * 
     * @return the string representation of tasks to be saved into text file
     */
    @Override
    public String toStorageData() {
        String completed = this.getStatusIcon();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d MMM u");
        String dateString = date.format(dateFormatter);
        return taskType + "//" + completed + "//" + description + "//" + dateString;
    }
}