package duke.task;

/**
 * A Todo is a Task that does not have any time limit
 */
public class Todo extends Task {
    /**
     * @param name a String indicating the name of the todo task
     * @param status an int indicating whether the task is done
     */
    public Todo(String name, int status) {
        super(name, status);
    }
    private int getStatusNo() {
        if (super.status.equals("[ ]")) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * Overrides toString method to display the type of the task, its status, and then its name.
     */
    @Override
    public String toString() {
        return "[T]" + status + " " + name;
    }

    /**
     * Changes the format of the Task String to facilitate local storage
     * @return a String to be written to local hard disk
     */
    @Override
    public String toStoreFormatString() {
        return String.format("T/%s/%d", super.name, this.getStatusNo());
    }
}
