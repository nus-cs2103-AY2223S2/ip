package duke.task;

/**
 * Class that represents tasks created by the user, can be marked as done or undone
 */
public class Task {
    protected String name;
    protected String status;

    /**
     * Constructor
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
     * Updates a task as done.
     */
    public void mark() {
        this.status = "[X]";
    }

    /**
     * Updates a task as undone
     */
    public void unmark() {
        this.status = "[ ]";
    }

    /**
     * Formats Task message to facilitate updating local tasks
     * @return a String to be written to the local hard disk
     */
    public String toStoreFormatString() {
        return "";
    }
}
