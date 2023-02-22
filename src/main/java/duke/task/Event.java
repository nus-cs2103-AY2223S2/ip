package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.List;

/**
 * Represents task type of event.
 */
public class Event extends Task {
    private final String taskType;
    private final LocalDateTime eventStartTime;
    private final LocalDateTime eventEndTime;
    DateTimeFormatter dateTimeFormatter1 =
            DateTimeFormatter.ofPattern("MMM dd yyyy HHmm a");

    public Event(int taskNumber, boolean taskStatus, String task,
                 LocalDateTime eventStartTime, LocalDateTime eventEndTime, int totalNumOfTasks) {
        super(taskNumber, taskStatus, task, totalNumOfTasks);
        this.taskType = "[E]";
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
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
                + " (from: " + this.eventStartTime.format(dateTimeFormatter1)
                + " to: "
                + this.eventEndTime.format(dateTimeFormatter1);
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
                + " (from: " + this.eventStartTime.format(dateTimeFormatter1)
                + " to: "
                + this.eventEndTime.format(dateTimeFormatter1);
    }

    /**
     * Prints task information when added to task list.
     *
     * @return String response that show task is added.
     */
    public String printEventTask() {
        return "\t Got it. I've added this task:"
                + "\n\t   [E]" + super.getTaskStatus() + " " + super.task
                + "(from: " + this.eventStartTime.format(dateTimeFormatter1)
                + " to: " + this.eventEndTime.format(dateTimeFormatter1) + ")"
                + "\n\t Now you have " + super.totalNumOfTasks + " tasks in the list.";
    }

    /**
     * Prints that task is deleted.
     *
     * @return String response that show task is deleted.
     */
    @Override
    public String printDelete(List<Task> allTasks) {
        int newTotalNumOfTasks = allTasks.size() - 1;
        return "\t Noted. I've removed this task:" + "\n\t   " + this.taskType
                + super.getTaskStatus() + " " + super.task + " (from: "
                + this.eventStartTime.format(dateTimeFormatter1) + " to: "
                + this.eventEndTime.format(dateTimeFormatter1) + ")" + "\n\t Now you have "
                + newTotalNumOfTasks + " tasks in the list.";
    }

    /**
     * Returns that this is an event task type.
     *
     * @return Task type.
     */
    @Override
    public String getTaskType() {
        return this.taskType;
    }

    /**
     * Get the starting time of event.
     *
     * @return Date and time of event start time.
     */
    @Override
    public LocalDateTime getEventStartTime() {
        return this.eventStartTime;
    }

    /**
     * Get the ending time of event.
     *
     * @return Date and time of event end time.
     */
    @Override
    public LocalDateTime getEventEndTime() {
        return this.eventEndTime;
    }

    /**
     * Get the date of event.
     *
     * @return Date of event.
     */
    @Override
    public LocalDate getDate() {
        return this.eventStartTime.toLocalDate();
    }
}
