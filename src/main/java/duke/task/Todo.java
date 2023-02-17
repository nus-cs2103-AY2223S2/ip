package duke.task;

/**
 * To do class represents a task of to-do type
 */
public class Todo extends Task {

    /**
     * constructor method to create a to-do type task
     * @param description name of to do
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * method to return to do task in string
     * @return to do task in string
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
