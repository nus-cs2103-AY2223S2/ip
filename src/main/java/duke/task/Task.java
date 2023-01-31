package duke.task;

public class Task {
    /**
     * @param name: a string indicating the name of the task
     * @param status: a boolean indicating whether the task is done or not
     */
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
     * @return a new duke.task.Task with status being true
     */
    public void mark() {
        this.status = "[X]";
    }
    /**
     * method to update a task as undone
     * @return a new duke.task.Task with status being false
     */
    public void unmark() {
        this.status = "[ ]";
    }

    public String toStoreFormatString() {
        return "";
    }
}