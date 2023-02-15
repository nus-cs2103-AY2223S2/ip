package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class for task with deadline
 */
public class Deadline extends Task {

    private String taskDescription;
    private LocalDate deadLine;

    /**
     * Constructor of Deadline task
     * @param taskString user taskString input
     * @param deadline deadline for the task
     */

    public Deadline(String taskString, LocalDate deadline) {
        super(taskString.substring(9, taskString.indexOf("/") - 1));

        taskDescription = taskString.substring(9, taskString.indexOf("/") - 1);
        deadLine = deadline;
    }

    @Override
    public String getTask() {
        return this.taskDescription;
    }

    @Override
    public String fullDetails() {
        return this.taskDescription + " " + this.getDeadline();
    }


    /**
     * Format the deadline date in the MMM d yyyy style
     * @return String of a formatted deadline
     */
    public String getDeadline() {
        return this.deadLine.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " ( by: " + this.getDeadline() + " )";
    }
}
