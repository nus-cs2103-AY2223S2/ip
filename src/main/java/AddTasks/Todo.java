package AddTasks;
public class Todo extends Task {

    /**
     * Constructor for Todo object.
     * @param description Name of the Todo object.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns string representation of the Todo object.
     * @return String representation of the Todo object and its marking status.
     */
    @Override
    public String toString() {
        return " " + "[T]" + "[" + super.getStatusIcon() + "] " + super.toString();
    }
}
