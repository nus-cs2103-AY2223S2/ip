package duke.tasks;

/**
 * Stores inputs of to-do tasks
 */
public class Todo extends Task {
    /**
     * Initialises to-do class
     *
     * @param name name of the task
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Displays name, date and time of the to-do task
     *
     * @return shows the to-do item
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
