package duke.task;

/**
 * Todo item of the user.
 */
public class Todo extends Task {

    /**
     * Constructor to create new duke.task
     *
     * @param task new duke.task to be added
     */
    public Todo(String task) {
        super(task);
    }

    /**
     * Convert the Todo duke.task into data from for duke.storage into file.
     * @return String representation of the data.
     */
    public String toData() {
        String status = this.completed ? "1" : "0";
        return "T | " + status + " |" + this.task;
    }

    @Override
    public String toString() {
        String statusIcon = this.completed ? "X" : " ";
        return "[T][" + statusIcon + "] " + this.task;
    }
}
