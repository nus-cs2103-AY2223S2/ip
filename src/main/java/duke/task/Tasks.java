package duke.task;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Represents the abstract concept of a task. It implements Serializable such that it can be
 * stored in local storage with ease.
 */
abstract public class Tasks implements Serializable {

    protected LocalDateTime time = null;
    private String content;
    private Boolean is_Done;
    protected char type;

    /**
     * Constructs an instance of Task with the given content and state of whether it is done.
     * @param content the content of the task.
     * @param is_Done whether the task is done.
     */
    public Tasks(String content, boolean is_Done) {
        this.content = content;
        this.is_Done = is_Done;
    }

    public String seeTaskContent() {
        return content;
    }

    public void markTask(boolean Done) {
        this.is_Done = Done;
    }

    /**
     * This method returns the string representation of whether a task is done.
     * @return String representation of the task status.
     */
    //Credits: Copied from https://nus-cs2103-ay2223s2.github.io/website/schedule/week2/project.html
    public String getStatusIcon() {
        return (this.is_Done ? "X" : " "); // mark done task with X
    }

    public char getTypeIcon() {
        return this.type; // mark done task with X
    }
}
