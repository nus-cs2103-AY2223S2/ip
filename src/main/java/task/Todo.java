package task;

import task.Task;

/**
 * Todo class inherits from the Task class and is used for representing all
 * todo tasks.
 *
 * @author      Tseng Chen-Yu
 * @version     %I%, %G%
 * @since       1.0
 */
public class Todo extends Task {

    /**
     * Constructor for Todo class.
     *
     * @param description Description for the todo task.
     */
    public Todo(String description){
        super(description);
    }

    /**
     * Overloaded constructor for Todo class.
     *
     * @param description Description for the todo task.
     * @param isDone Todo task completion status.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Overrides the default toString method.
     *
     * @return A text UI representing a todo entry, displaying the task type, completion status and description.
     */
    @Override
    public String toString() {
        return "[T]" + "[" + super.getStatusIcon() + "] " + super.toString();
    }
}
