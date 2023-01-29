package hachi.tasks;

/**
 * Encapsulates a Task without a date.
 */
public class Todo extends Task {

    /**
     * Todo constructor.
     *
     * @param input The description of the task.
     */
    public Todo(String input) {
        super(input);
    }

    /**
     * Returns the string representation of the Todo object.
     *
     * @return String representation of the Todo object.
     */
    public String toString() {
        return "   [T]" + super.toString();
    }
}
