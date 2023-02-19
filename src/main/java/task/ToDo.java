package task;

/**
 * To-Do extends Task and is used to represent a To-Do task.
 * This class extends the Task class and implements its abstract method save().
 *
 * @author Tan Yan-Hao Joshua
 */
public class ToDo extends Task {

    /**
     * Constructs a To-Do task with the given description.
     *
     * @param description a string that describes the task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Overridden save() method for To-Do.
     * The method returns a string representation of the To-Do task.
     *
     * @return A string representation of the To-Do task in a specific format.
     */
    @Override
    public String save() {
        return this.toString();
    }

    /**
     * Overridden toString() method for To-Do.
     * The method returns a string representation of the To-Do task.
     *
     * @return A string representation of the To-Do task in a specific format.
     */
    @Override
    public String toString() {
        return "[T]" +
                super.toString();
    }
}
