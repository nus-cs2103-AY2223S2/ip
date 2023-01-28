package duke;

/**
 * Task is the parent class of ToDo, Event and Deadline. It contains description
 * of task and its completion status.
 */
public abstract class Task {

    public static final String SEPARATOR = " | ";
    String description;
    boolean isDone;

    /**
     * Constructor for Task
     * @param description Details of the task
     * @param isDone Keeps track of whether task is completed
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Same as above constructor, except isDone is initalised to false
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    String getDescription() {
        return this.description;
    }
    /**
     * Converts true to 1 and false to 0 to be saved in duke.txt
     * @return 1 if true else 0
     */
    String convertBoolean() {
        return (this.isDone) ? "1" : "0";
    }

    String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Sets task as completed
     */
    void setAsDone() {
        isDone = true;
    }

    /**
     * Sets task as uncompleted
     */
    public void setAsUndone(){
        isDone = false;
    }


    public abstract String toSave();
}

