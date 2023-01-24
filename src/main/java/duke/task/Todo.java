package duke.task;

/**
 * Represents a task with the category of todo.
 */
public class Todo extends Task {

    public Todo(String content) {
        super(content);

    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
