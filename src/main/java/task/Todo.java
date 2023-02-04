package task;

import parser.Parser;

/**
 * Todo task without any datetime field.
 */
public class Todo extends Task {

    public Todo(String content, boolean isHigh) {
        super(content, isHigh);
    }

    /**
     * Creates a new Todo object.
     * @param content The content of the todo.
     * @return A new Todo object.
     */
    public static Todo create(String content, boolean isHigh) {
        Parser.handleEmptyField(content, "content", "Task.Todo Creation");
        return new Todo(content, isHigh);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toStorageString() {
        return String.format("T|%d|%s", this.isMarked() ? 1 : 0, this.getContent());
    }
}
