package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.List;

/**
 * Represents task type of deadline.
 */
public class Deadline extends Task {
    private final String taskType;
    private final LocalDateTime taskDeadline;
    DateTimeFormatter dateTimeFormatter1 =
            DateTimeFormatter.ofPattern("MMM dd yyyy HHmm a");

    public Deadline(int taskNumber, boolean isMarked, String task, LocalDateTime taskDeadline, int totalNumOfTasks) {
        super(taskNumber, isMarked, task, totalNumOfTasks);
        this.taskType = "[D]";
        this.taskDeadline = taskDeadline;
    }

    /**
     * Prints that task is marked as done when task is done.
     *
     * @return String response that show task is marked as done.
     */
    @Override
    public String markAsDone() {
        return "\t Nice! I've marked this task as done:\n"
                + "\t  " + this.taskType + "[X]" + " " + super.task
                + " (by: " + this.taskDeadline.format(dateTimeFormatter1) + ")";
    }

    /**
     * Prints that task is marked as not done when task is unmarked.
     *
     * @return String response that show task is unmarked as undone.
     */
    @Override
    public String unmarkAsUndone() {
        return "\t OK, I've marked this task as not done yet:\n"
                + "\t  " + this.taskType + "[ ]" + " " + super.task
                + " (by: " + this.taskDeadline.format(dateTimeFormatter1) + ")";
    }

    /**
     * Prints task information when added to task list.
     *
     * @return String response that show task is added.
     */
    public String printDeadlineTask() {
        return "\t Got it. I've added this task:"
                + "\n\t   [D]" + super.getTaskStatus() + " " + super.task
                + "(by: " + this.taskDeadline.format(dateTimeFormatter1) + ")"
                + "\n\t Now you have " + super.totalNumOfTasks + " tasks in the list.";
    }

    /**
     * Prints that task is deleted.
     *
     * @return String response that show task is deleted.
     */
    @Override
    public String printDelete(List<Task> allTasks) {
        return "\t Noted. I've removed this task:" + "\n\t   " + this.taskType
                + super.getTaskStatus() + " " + super.task + " (by: "
                + this.taskDeadline.format(dateTimeFormatter1) + ")"
                + "\n\t Now you have " + allTasks.size() + " tasks in the list.";
    }

    /**
     * Returns that this is a deadline task type.
     *
     * @return Type of task.
     */
    @Override
    public String getTaskType() {
        return this.taskType;
    }

    /**
     * Gets task deadline.
     *
     * @return Date and time of deadline.
     */
    @Override
    public LocalDateTime getDeadline() {
        return this.taskDeadline;
    }

    /**
     * Gets date of task deadline.
     *
     * @return Date of deadline.
     */
    @Override
    public LocalDate getDate() {
        return this.taskDeadline.toLocalDate();
    }
}
