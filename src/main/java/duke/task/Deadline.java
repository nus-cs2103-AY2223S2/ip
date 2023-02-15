package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class for task with deadline
 */
public class Deadline extends Task {

    private String taskDescription;
    private LocalDate deadLine;
    private String deadlineString;

    /**
     * Constructor of Deadline task
     * @param taskString user taskString input
     * @param deadline deadline for the task
     */

    public Deadline(String taskString, String deadline) {
        super(taskString);

        taskDescription = taskString;
        deadLine = LocalDate.parse(deadline);
        deadlineString = deadline;
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

    @Override
    public String toBeSaved() {
        return "D" + "///" + super.toBeSaved() + "///" + this.deadlineString + "///" + "null";
    }
}
