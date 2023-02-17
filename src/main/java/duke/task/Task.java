package duke.task;

//adapted from partial solution provided in lecture notes
//https://nus-cs2103-ay2223s2.github.io/website/admin/ip-w2.html

/**
 * Task that has description, type and variable to determine if it is done or not.
 */
public abstract class Task {
    private String desc;
    private String type;
    private boolean isDone;

    /**
     * Constructor for Task.
     * @param desc
     */
    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
        this.type = null;
    }

    /**
     * Constructor for Task.
     * @param desc
     * @param type
     */
    public Task(String desc, String type) {
        this.desc = desc;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Gets type of task.
     * @return type of task
     */
    public String getType() {
        return this.type;
    }

    /**
     * Gets status icon of task.
     * @return status icon of task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Gets description of task.
     * @return description of task
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Unmarks task.
     */
    public void markUndone() {
        isDone = false;
    }

    /**
     * Prints string representation of task.
     * @return string representation of task
     */
    public String toString() {
        return String.format("[%s][%s] %s", this.type, this.getStatusIcon(), this.getDesc());
    }
}
