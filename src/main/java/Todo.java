/**
 * The To-do class is a type of task.
 *
 * @author Chia Jeremy
 */

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
