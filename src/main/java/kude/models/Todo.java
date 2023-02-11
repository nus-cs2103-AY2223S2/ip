package kude.models;

/**
 * Representation of a Todo item
 */
public class Todo extends Task {
    public Todo(String content) {
        super(content);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
