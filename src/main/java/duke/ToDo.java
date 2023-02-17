package duke;

/**
 * Class duke.ToDo is a subclass of duke.Task, encapsulate details
 * about a task that the user need to do.
 *
 * @author hhchinh2002
 */
public class ToDo extends Task {
    /**
     * Creates a duke.ToDo task object with given description
     *
     * @param description The description for the duke.ToDo task
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ToDo) {
            ToDo target = (ToDo) o;
            return target.getDescription().equals(this.getDescription());
        }
        return false;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
