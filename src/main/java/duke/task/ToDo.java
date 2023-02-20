package duke.task;

/**
 * Class duke.task.ToDo is a subclass of duke.task.Task, encapsulate details
 * about a task that the user need to do.
 *
 * @author hhchinh2002
 */
public class ToDo extends Task {
    /**
     * Creates a duke.task.ToDo task object with given description
     *
     * @param description The description for the duke.task.ToDo task
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
