package duke.task;

/**
 * class that represents tasks created by the user, can be marked as done or undone
 */
public class Task {
    protected String name;
    protected String status;

    /**
     * Constructor
     * 
     * @param name the name of the task
     * @param status a number that indicates the task is done if it is 1
     */
    public Task(String name, int status) {
        this.name = name;
        if (status == 1) {
            this.status = "[X]";
        } else {
            this.status = "[ ]";
        }
    }

    /**
     * method to update a task as done.
     */
    public void mark() {
        this.status = "[X]";
    }

    /**
     * method to update a task as undone
     */
    public void unmark() {
        this.status = "[ ]";
    }

    /**
     * a method that helps with updating local hard disk
     * 
     * @return a Strnig to be written to the local hard disk
     */
    public String toStoreFormatString() {
        return "";
    }
}
