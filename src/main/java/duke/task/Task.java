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
    protected Priority priority;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.priority = Priority.MEDIUM;
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
        return isDone + " " + this.getPriority() + " " + this.description;
    }

    /**
     * Display completion status of task
     * @return String symbol whether a task is completed
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done duke.task with X
    }

    /**
     * Get priority status of the task
     * @return int corresponding to LOW,MEDIUM, or HIGH (3,2,1)
     */
    public int getPriority() {
        switch(this.priority) {
            case LOW:
                return 3;
            case MEDIUM:
                return 2;
            case HIGH:
                return 1;
            default:
                return -1;
        }
    }

    /**
     * Set new priority status of task
     * @param newPriority
     */
    public void setPriority(int newPriority) {
        switch (newPriority) {
            case 1:
                this.priority = Priority.HIGH;
                break;
            case 2:
                this.priority = Priority.MEDIUM;
                break;
            case 3:
                this.priority = Priority.LOW;
                break;
            default:
                assert  false: "invalid priority";
        }
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
        return "[" + this.getStatusIcon() + "]"
                + "[" + this.getPriority() + "] "
                + this.description;
    }


}

