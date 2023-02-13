package duke;

/**
 *  The superclass of all types of Tasks created by the user
 */
public class Task {
    private final String details;
    private boolean status;

    /**
     * Constructor of the Task class
     * @param details details of the task
     */
    public Task(String details) {
        this.details = details;
        this.status = false;
    }

    /**
     * Indicating if the task is done
     * @return a String type
     */
    public String isDone() {
        if (status) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    /**
     * A method to change status to true indicating task is done
     */
    public void mark() {
        status = true;
    }

    /**
     * A method to change status to true indicating task is not done
     */
    public void unmark() {
        status = false;
    }

    /**
     * @return description of the task
     */
    @Override
    public String toString() {
        if (status && (details.contains("[ ]") || details.contains("[X]"))) {
            details.replace("[ ]", "[X]");
            return details;
        } else if (!status && (details.contains("[ ]") || details.contains("[X]"))) {
            details.replace("[X]", "[ ]");
            return details;
        } else if (!details.contains("[ ]") || !details.contains("[X]")) {
            return isDone() + " " + this.details;
        } else {
            return this.details;
        }
    }
}
