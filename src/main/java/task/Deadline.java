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
     * @param priorityLevel Priority level of task.
     */
    public Deadline(String taskName, String endDate, Boolean isTaskDone, PriorityLevel priorityLevel) {
        super(taskName, isTaskDone, priorityLevel);
        this.endDate = LocalDate.parse(endDate, DateTimeFormatter.ofPattern(this.getDateFormatA()));
    }

    @Override
    public String writeToFile() {
        return this.formatForWriteToFile(this.isDone(), this.getName(),
                this.endDate, this.getPriority());
    }

    private String formatForWriteToFile(Boolean isDone, String taskName,
                                        LocalDate endDate, PriorityLevel priorityLevel) {

        StringBuilder s = new StringBuilder("D");

        if (priorityLevel.equals(PriorityLevel.HIGH)) {
            s.append("|h|");
        } else if (priorityLevel.equals(PriorityLevel.MID)) {
            s.append("|m|");
        } else {
            s.append("|l|");
        }

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
        return this.formatForUserToSee(this.isDone(), this.getName(),
                this.endDate, this.getPriority());
    }

    private String formatForUserToSee(Boolean isDone, String taskName,
                                      LocalDate endDate, PriorityLevel priorityLevel) {
        StringBuilder s = new StringBuilder(priorityLevel.toString() + " [D][");

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
