package duke.task;

/**
 *      File name: Todo.java
 *      @author: Jerome Neo
 *      Description: Todo class that inherits from the Task class.
 */
public class Todo extends Task {

    /**
     * Constructor for Todo class.
     *
     * @param description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo object.
     *
     * @return a string.
     */
    @Override
    public String toString() {
        return "[T]" + "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}

