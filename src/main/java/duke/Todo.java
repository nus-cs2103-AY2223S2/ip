package duke;

/**
 * Todo class that is an extension of Task class.
 */
public class Todo extends Task {

    /**
     * Constructor for Todo class.
     * Returns Todo task with status set as NOT_DONE.
     *
     * @param description String description of Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructor for Todo class to be mainly used by Storage class to load tasks from data.txt file.
     * Returns Todo task with specified status.
     *
     * @param description String description of Todo task.
     * @param status Status of the task.
     */
    public Todo(String description, String status) {
        super(description, status);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts Todo data into String to be used to save task data.
     * Returns String of Todo in a format to be saved and loaded in the future.
     */
    @Override
    public String toData() {
        return "T|" + super.toData();
    }

}
