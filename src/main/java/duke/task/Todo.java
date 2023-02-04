package duke.task;

/**
 * Type todo that is inherited from task
 */
public class Todo extends Task {

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String parse() {
        return "T" + " | " + super.parse();
    }
}
