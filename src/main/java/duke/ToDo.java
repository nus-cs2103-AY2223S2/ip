package duke;

/**
 * A ToDo represents a type of Task that can be added to the TaskList
 */
public class ToDo extends Task {

    /**
     * Constructor for the ToDo task type
     * @param description A brief description of the task
     */
    public ToDo(String description) {
        super(description);

    }


    @Override
    public String toString() {
        return (isDone? "[T][X] " : "[T][ ] ") + description;
    }
}
