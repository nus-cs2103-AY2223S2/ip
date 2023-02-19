package Duke.Tasks;

import Duke.Tasks.Task;

/**
 * Class representing a ToDo task.
 * @author Bryan Juniano
 */

public class ToDo extends Task {
    /**
     * Constructor for ToDo task.
     * @param name of the ToDo task.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Generates the string representation of the ToDo task.
     */
    @Override
    public String toString() {
        return "[T] | " + super.toString();
    }
}