package duke.model;

/**
 * The Todo class represents a todo, a task that does not have a specific date and time associated with it.
 * @author jayanth
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
