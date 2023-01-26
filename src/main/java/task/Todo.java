package task;

/**
 * Represents a task that is to be done
 */
public class Todo extends Task {

    /**
     * Creates a task with the given text that is to be done
     * @param text a String description of the task
     */
    public Todo(String text) {
        super(text);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
