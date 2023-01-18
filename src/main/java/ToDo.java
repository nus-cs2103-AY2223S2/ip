/**
 * ToDo is a type of Task.
 * This is the most primitive version of Task
 * @author EL
 */
public class ToDo extends Task {

    /**
     * Constructor that takes in the name of the Task.
     * @param task_name Name of Task
     */
    public ToDo(String task_name) {
        super(task_name);
    }

    /**
     * Returns String representation of Todo.
     * @return The string representation of this Todo.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
