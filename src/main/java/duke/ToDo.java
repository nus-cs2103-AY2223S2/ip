package duke;

/**
 * Represents a ToDo entered by the user.
 */
public class ToDo extends Task {

    /**
     * Constructor for the ToDo.
     *
     * @param taskDescription Description of the todo.
     */
    public ToDo(String taskDescription) {
        setTaskDescription(taskDescription);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
