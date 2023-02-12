package task;

import task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event is a type of task which includes the details, start date and end date.
 */
public class Event extends Task {

    /** Start date of this task */
    private LocalDate startTime;

    /** End date of this task */
    private LocalDate endTime;

    /**
     * Creates an Event object.
     *
     * @param s Details of event.
     * @param startTime Start date of event.
     * @param endTime End date of event.
     */
    public Event(String s, String startTime, String endTime) {
        super(s);
        this.startTime = LocalDate.parse(startTime, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.endTime = LocalDate.parse(endTime, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    /**
     * Creates an Event object.
     * Use when reading from file.
     *
     * @param isTaskDone Status of event.
     * @param taskDetails Details of event.
     * @param taskDate Start and end date of event.
     */
    public Event(Boolean isTaskDone, String taskDetails, String taskDate) {
        super(taskDetails);
        if (isTaskDone) {
            this.markDone();
        }
        String from = taskDate.substring(0, taskDate.indexOf("|"));
        String to = taskDate.substring(taskDate.indexOf("|") + 1);

        this.startTime = LocalDate.parse(from, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.endTime = LocalDate.parse(to, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @Override
    public String writeToFile() {
        if (!isTaskDone) {
            return "E| |"
                    + this.taskName
                    + "|"
                    + this.startTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                    + "|"
                    + this.endTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }
        return "E|X|"
                + this.taskName
                + "|"
                + this.startTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                + "|"
                + this.endTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @Override
    public String toString() {
        if (!isTaskDone) {
            return "[E][ ] " + this.taskName
                + " (from: " + this.startTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                + " to: " + this.endTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + ")";
        }
        return "[E][X] " + this.taskName
                + " (from: " + this.startTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                + " to: " + this.endTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + ")";
    }

}
