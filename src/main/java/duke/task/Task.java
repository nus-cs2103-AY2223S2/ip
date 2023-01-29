package duke.task;
import java.util.List;
import java.util.ArrayList;
/**
 * Abstract class Task for Todo, Deadline, and Event to inherit from
 *
 * @author Pearl Twe
 * @version CS2103T AY22/23 Semester 2
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Return description of task
     * @return description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Encode task into String for easier decode when tasks from loading duke.txt
     * @return String format of task
     */
    public String encode() {
        return this.description + isDone;
    }

    /**
     * Display completion status of task
     * @return String symbol whether a task is completed
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done duke.task with X
    }

    /**
     * Mark task as done
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Mark task as undone
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Convert task into String for display in taskList
     * @return String of status and task description
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }


}

