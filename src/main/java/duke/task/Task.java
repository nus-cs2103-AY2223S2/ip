package duke.task;

import java.time.LocalDate;
import java.util.List;
import java.time.LocalDateTime;

/**
 * Represents a Task.
 * Task information are stored here.
 */
public class Task {
    protected final int taskNumber;
    protected final boolean taskStatus;
    protected final String task;
    protected final int totalNumOfTasks;

    Task(int taskNumber, boolean taskStatus, String task, int totalNumOfTasks) {
        this.taskNumber = taskNumber;
        this.taskStatus = taskStatus;
        this.task = task;
        this.totalNumOfTasks = totalNumOfTasks;
    }

    /**
     * Prints that task is marked as done when task is done.
     */
    public void markAsDone() {
        System.out.println("\t____________________________________________________________" +
                "\n\t Nice! I've marked this task as done:\n" +
                "\t  " + "[X]" + " " + this.task +
                "\n\t____________________________________________________________");
    }

    /**
     * Prints that task is marked as not done when task is unmarked.
     */
    public void unmarkAsUndone() {
        System.out.println("\t____________________________________________________________" +
                "\n\t OK, I've marked this task as not done yet:\n" +
                "\t  " + "[ ]" + " " + this.task +
                "\n\t____________________________________________________________");
    }

    /**
     * Prints that task is deleted.
     */
    public void printDelete(List<Task> allTasks) {
        int allTaskSize = allTasks.size() - 1;
        System.out.println("\t____________________________________________________________" +
                "\n\t Noted. I've removed this task:" + "\n\t   " +
                this.getTaskStatus() + " " + this.task +
                "\n\t Now you have " + allTaskSize + " tasks in the list.");
    }

    /**
     * Gets task number.
     */
    public int getTaskNumber() {
        return this.taskNumber;
    }

    /**
     * Gets type of task: Todo, Deadline, Event.
     */
    public String getTaskType() {
        return "";
    }

    /**
     * Gets whether task is done.
     */
    public String getTaskStatus() {
        if (!this.taskStatus) {
            return "[ ]";
        } else {
            return "[X]";
        }
    }

    /**
     * Gets task.
     */
    public String getTask() {
        return this.task;
    }

    public LocalDateTime getDeadline() {
        return null;
    }

    public LocalDateTime getEventStartTime() {
        return null;
    }

    public LocalDateTime getEventEndTime() {
        return null;
    }

    public LocalDate getDate() {
        return null;
    }

    /**
     * To override equals such that same type of object can pass Junit
     * test.
     */
    @Override
    public boolean equals(Object o) {
        return getClass() == o.getClass();
    }
}
