/**
 * Represents a todo task.
 *
 * @author wz2k
 */
public class Todo extends Task {
    /**
     * Constructor for Todo class.
     *
     * @param desc description of the todo task.
     */
    public Todo(String desc, boolean marked) {
        super(desc, marked);
    }

    /**
     * This method returns the task type, checkbox and description.
     *
     * @return todo task details.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toTaskStorageString() {
        return "T" + "|" + super.toTaskStorageString();
    }
}
