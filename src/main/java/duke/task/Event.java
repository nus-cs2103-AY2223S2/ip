package duke.task;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
     */
    @Override
    public void markAsDone() {
        System.out.println("\t____________________________________________________________" +
                "\n\t Nice! I've marked this task as done:\n" +
                "\t  " + this.taskType + "[X]" + " " + super.task +
                " (from: " + this.eventStartTime.format(dateTimeFormatter1)
                + " to: "
                + this.eventEndTime.format(dateTimeFormatter1) +
                "\n\t____________________________________________________________");
    }

    /**
     * Prints that task is marked as not done when task is unmarked.
     */
    @Override
    public void unmarkAsUndone() {
        System.out.println("\t____________________________________________________________" +
                "\n\t OK, I've marked this task as not done yet:\n" +
                "\t  " + this.taskType + "[ ]" + " " + super.task +
                " (from: " + this.eventStartTime.format(dateTimeFormatter1)
                + " to: "
                + this.eventEndTime.format(dateTimeFormatter1) +
                "\n\t____________________________________________________________");
    }

    /**
     * Prints task information when added to task list.
     */
    public void printEventTask() {
        System.out.println("\t____________________________________________________________" +
                "\n\t Got it. I've added this task:" +
                "\n\t   [E]" + super.getTaskStatus() + " " + super.task +
                "(from: " + this.eventStartTime.format(dateTimeFormatter1) +
                " to: " + this.eventEndTime.format(dateTimeFormatter1) + ")" +
                "\n\t Now you have " + super.totalNumOfTasks + " tasks in the list." +
                "\n\t____________________________________________________________");
    }

    /**
     * Prints that task is deleted.
     */
    @Override
    public void printDelete(List<Task> allTasks) {
        int newTotalNumOfTasks = allTasks.size() - 1;
        System.out.println("\t____________________________________________________________" +
                "\n\t Noted. I've removed this task:" +
                "\n\t   " + this.taskType +
                super.getTaskStatus() + " " + super.task + " (from: " +
                this.eventStartTime.format(dateTimeFormatter1) +
                " to: " +
                this.eventEndTime.format(dateTimeFormatter1) +
                ")" + "\n\t Now you have " +
                newTotalNumOfTasks + " tasks in the list." +
                "\n\t____________________________________________________________");
    }

    /**
     * Returns that this is an event task type.
     */
    @Override
    public String getTaskType() {
        return this.taskType;
    }

    /**
     * Get the starting time of event.
     */
    @Override
    public LocalDateTime getEventStartTime() {
        return this.eventStartTime;
    }

    /**
     * Get the ending time of event.
     */
    @Override
    public LocalDateTime getEventEndTime() {
        return this.eventEndTime;
    }

    /**
     * Get the date of event.
     */
    @Override
    public LocalDate getDate() {
        return this.eventStartTime.toLocalDate();
    }
}
