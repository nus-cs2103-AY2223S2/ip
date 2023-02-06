package duke.task;

/**
 * ToDo class for ToDo Tasks.
 */
public class ToDo extends Task {

    /**
     * Constructor for the ToDo class.
     * @param description the description of the Task.ToDo
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Represents the ToDo as a String.
     * @return String representation of the ToDo.
     */
    @Override
    public String toString() {
        return "[T]" + super.getStatusIcon() + " " + super.getDescription();
    }

    /**
     * Adds a ToDo
     * @param details user input details for creating the ToDo.
     * @return the newly constructed ToDo object.
     */
    public static ToDo addToDo(String details) {
        return new ToDo(details);
    }
}
