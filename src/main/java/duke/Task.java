package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {

    protected String name;
    protected boolean isDone;
    protected String taskType;

    public Task(String name, String taskType) {
        this.name = name;
        this.isDone = false;
        this.taskType = taskType;
    }

    public Task(String name, String taskType, boolean isDone) {
        this.name = name;
        this.taskType = taskType;
        this.isDone = isDone;
    }

    /**
     * Returns <code>X</code> if the task is done and
     * a blank space if the task is not done.
     * @return <code>"X"</code> or <code>" "</code> depending on the status
     * of the task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the name of the task.
     * @return name of task
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the type of task this <code>Task</code> is.
     * @return type of task
     */
    public String getTaskType() {
        return this.taskType;
    }

    /**
     * Returns the status of this <code>Task</code>.
     * @return true if task is done and false if task is not done
     */
    public boolean getTaskStatus() {
        return this.isDone;
    }

    /**
     * Marks task as undone.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks task as done.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Describes the <code>Task</code> object.
     * @return a <code>String</code> for output to users.
     */
    public String getDescription() {
        return String.format("[%s][%s] %s", this.taskType, this.getStatusIcon(), this.name);
    }

    /**
     * Formats the task so that it can be added into the task list.
     * @return a <code>String</code> to be added into the task list representing
     * this <code>Task</code> object.
     */
    public String formatDescription() {
        String status = isDone ? "1" : "0";
        String description = String.format("%s | %s | %s", this.taskType,
                status, this.name);
        return description;
    }

    public static LocalDateTime makeDateTime(String date) {
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
    }

    public static String formatDateTimeForTaskList(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
