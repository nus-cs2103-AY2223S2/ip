package duke.task;

public class Task {
    protected String name;
    protected String status;

    public Task(String name, int status) {
        this.name = name;
        if (status == 1) {
            this.status = "[X]";
        } else {
            this.status = "[ ]";
        }
    }
    /**
     * overrides the toString method
     */
    public String toString() {
        return "." + status + " " + name;
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

    public String toStoreFormatString() {
        return "";
    }
}
