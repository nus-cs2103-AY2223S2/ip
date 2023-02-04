package duke.tasks;

/**
 * A class representing a ToDo task.
 */
public class ToDo extends Task {

    /**
     * Constructor for a ToDo task.
     * @param name
     */
    public ToDo(String name) {
        super(name);
    }

    public String toSaveFormat() {
        return String.format("T,%s", this.name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
