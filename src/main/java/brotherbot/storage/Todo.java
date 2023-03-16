package brotherbot.storage;

public class Todo extends Task {

    /**
     * Constructor to create a Todo object.
     *
     * @param description Description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Prints type, status and description of Task.
     *
     * @return String representation of Todo object.
     */
    @Override
    public String toString() {
        return "[T] " + "[" + this.getStatusIcon() + "] " + this.description;
    }

}
