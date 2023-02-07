package task;

/**
 * One of the three types of tasks a user can add.
 * Does not have any date elements.
 */
public class Todo extends Task {

    public Todo(String instruction) {
        super(instruction.substring(5));
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
