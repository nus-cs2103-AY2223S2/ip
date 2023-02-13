package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event is a type of task which includes the name, start date and end date.
 */
public class Event extends Task {

    private final LocalDate startDate;

    private final LocalDate endDate;

    /**
     * Constructor for event type of task with default isTaskDone.
     *
     * @param taskName Name of task.
     * @param startDate Start date of task.
     * @param endDate End date of task.
     */
    public Event(String taskName, String startDate, String endDate) {
        super(taskName);
        this.startDate = LocalDate.parse(startDate, DateTimeFormatter.ofPattern(this.getDateFormatA()));
        this.endDate = LocalDate.parse(endDate, DateTimeFormatter.ofPattern(this.getDateFormatA()));
    }

    /**
     * Constructor for event type of task.
     *
     * @param taskName Name of task.
     * @param startDate Start date of task.
     * @param endDate End date of task.
     * @param isTaskDone Status of task.
     */
    public Event(String taskName, String startDate, String endDate, Boolean isTaskDone) {
        super(taskName, isTaskDone);
        this.startDate = LocalDate.parse(startDate, DateTimeFormatter.ofPattern(this.getDateFormatA()));
        this.endDate = LocalDate.parse(endDate, DateTimeFormatter.ofPattern(this.getDateFormatA()));
    }

    @Override
    public String writeToFile() {
        return this.formatForWriteToFile(this.isDone(), this.getName(), this.startDate, this.endDate);
    }

    private String formatForWriteToFile(Boolean isDone, String taskName,
                                        LocalDate startDate, LocalDate endDate) {
        StringBuilder s = new StringBuilder("E|");

        if (isDone) {
            s.append("X");
        } else {
            s.append(" ");
        }

        s.append("|");
        s.append(taskName);
        s.append("|");
        s.append(startDate.format(DateTimeFormatter.ofPattern(this.getDateFormatA())));
        s.append("|");
        s.append(endDate.format(DateTimeFormatter.ofPattern(this.getDateFormatA())));

        return s.toString();
    }

    @Override
    public String toString() {
        return this.formatForUserToSee(this.isDone(), this.getName(), this.startDate, this.endDate);
    }

    private String formatForUserToSee(Boolean isDone, String taskName,
                                      LocalDate startDate, LocalDate endDate) {
        StringBuilder s = new StringBuilder("[E][");

        if (isDone) {
            s.append("X");
        } else {
            s.append(" ");
        }
        s.append("] ");
        s.append(taskName);
        s.append(" (from: ");
        s.append(startDate.format(DateTimeFormatter.ofPattern(this.getDateFormatB())));
        s.append(" to: ");
        s.append(endDate.format(DateTimeFormatter.ofPattern(this.getDateFormatB())));
        s.append(")");

        return s.toString();
    }
}
