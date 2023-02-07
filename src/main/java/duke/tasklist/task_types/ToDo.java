package duke.tasklist.task_types;

/**
 * Represents a <code>task</code> object with the todo tag into it.
 * 
 * @author Brian Quek
 */
public class ToDo extends Task {

    /**
     * Constructor for the ToDo object.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * @return a String containing the task type and todo details.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

}
