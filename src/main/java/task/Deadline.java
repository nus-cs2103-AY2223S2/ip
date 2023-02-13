package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a task which includes the name and end date.
 */
public class Deadline extends Task {

    private final LocalDate endDate;

    /**
     * Constructor for Deadline with default isTaskDone.
     *
     * @param taskName Name of task.
     * @param endDate End date of task.
     */
    public Deadline(String taskName, String endDate) {
        super(taskName);
        this.endDate = LocalDate.parse(endDate, DateTimeFormatter.ofPattern(this.getDateFormatA()));
    }

    /**
     * Constructor for Deadline.
     *
     * @param taskName Name of task.
     * @param endDate End date of task.
     * @param isTaskDone Status of task.
     */
    public Deadline(String taskName, String endDate, Boolean isTaskDone) {
        super(taskName, isTaskDone);
        this.endDate = LocalDate.parse(endDate, DateTimeFormatter.ofPattern(this.getDateFormatA()));
    }

    @Override
    public String writeToFile() {
        return this.formatForWriteToFile(this.isDone(), this.getName(), this.endDate);
    }

    private String formatForWriteToFile(Boolean isDone, String taskName, LocalDate endDate) {
        StringBuilder s = new StringBuilder("D|");

        if (isDone) {
            s.append("X");
        } else {
            s.append(" ");
        }

        s.append("|");
        s.append(taskName);
        s.append("|");
        s.append(endDate.format(DateTimeFormatter.ofPattern(this.getDateFormatA())));

        return s.toString();
    }

    @Override
    public String toString() {
        return this.formatForUserToSee(this.isDone(), this.getName(), this.endDate);
    }

    private String formatForUserToSee(Boolean isDone, String taskName, LocalDate endDate) {
        StringBuilder s = new StringBuilder("[E][");

        if (isDone) {
            s.append("X");
        } else {
            s.append(" ");
        }
        s.append("] ");
        s.append(taskName);
        s.append(" (by: ");
        s.append(endDate.format(DateTimeFormatter.ofPattern(this.getDateFormatB())));
        s.append(")");

        return s.toString();
    }
}
