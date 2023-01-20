package duke.tasks;

/**
 * Todos task which only stores the name of the task to do.
 *
 * @author Cheam Jia Wei
 */
public class Todos extends Task {
    public Todos(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toWrite() {
        return "T | " + super.toWrite() + "\n";
    }
}
