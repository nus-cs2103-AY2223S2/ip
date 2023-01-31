package Duke;

/**
 * Class representing a ToDo task.
 * @author Bryan Juniano
 */

public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T] | " + super.toString();
    }
}