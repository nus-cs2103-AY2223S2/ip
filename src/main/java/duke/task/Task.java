package duke.task;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Represents the abstract concept of a task. It implements Serializable such that it can be
 * stored in local storage with ease.
 */
abstract public class Task implements Serializable {
    protected enum type {
        T, //Todo
        D, //Deadline
        E; //Event
    }
    protected LocalDateTime time = null;
    private String taskContent;
    private Boolean isDone;
    protected type taskType;

    abstract public void postponeTask(String ... dates);

    /**
     * Constructs an instance of Task with the given content and state of whether it is done.
     * @param content the content of the task.
     * @param whetherDone whether the task is done.
     */
    public Task(String content, boolean whetherDone) {
        this.taskContent = content;
        this.isDone = whetherDone;
    }

    public LocalDateTime getTime() {
        return this.time;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void markTask(boolean Done) {
        this.isDone = Done;
    }

    /**
     * Returns the string representation of whether a task is done.
     * @return String representation of the task status.
     */
    //Credits: Copied from https://nus-cs2103-ay2223s2.github.io/website/schedule/week2/project.html
    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    public type getTypeIcon() {
        return this.taskType; // mark done task with X
    }
}
