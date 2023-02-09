package duke.tasks;

/**
 * Represents Task object of todo type.
 */
public class Todo extends Task {

    /**
     * Creates Todo object with parent constructor
     * @param description Task description of the Todo object
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Generates a data string representation of the Task object that will be used
     * to store the Task object in storage file.
     *
     * @return Data string of the task
     */
    public String getData() {
        StringBuilder sb = new StringBuilder();
        sb.append("T | ");
        if (isTaskDone()) {
            sb.append("1 | ");
        } else {
            sb.append("0 | ");
        }
        sb.append(this.description).append("\n");
        return sb.toString();
    }

    /**
     * Returns string representation of the task
     *
     * @return String representation of the task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
