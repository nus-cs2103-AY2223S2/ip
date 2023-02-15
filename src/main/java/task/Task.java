package task;

/**
 * Task is an object that contains all information about a task
 * @author CShuwen
 * @version 1.0
 * @since 0.0
 */
public class Task {
    protected String description;
    private int isDone;

    /**
     * Create a new Task
     *
     * @param description decription of a task.
     * @param isDone 0 represents the task is not done and 1 represents the task is done.
     */
    public Task(String description, Integer isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    /**
     * return the description of a task.
     *
     * @return the description of a task.
     */
    public String getDes() {
        return description;
    }

    /**
     * Return an icon representation of whether a task is done
     *
     * @return an icon of "[ ]" when the task is not done and "[X]" otherwise .
     */
    public String getStatusIcon() {
        return (isDone == 1 ? "[X]" : "[ ]");
    }
    /**
     * Mark a task as done
     */
    public void markAsDone() {
        isDone = 1;
    }

    /**
     * Mark a task as undone.
     */
    public void unMark() {
        isDone = 0;
    }

    /**
     * Returns a String representation of the task, the purpose is to be stored in duke.txt
     *
     * @return A String representaton of a task.
     */
    public String dataFormat() {
        return "Unknow task";
    }

    /**
     * Returns a String description of a task
     *
     * @return String description of a task.
     */
    public String toString() {
        return this.getStatusIcon() + " " + this.getDes();
    }
}
