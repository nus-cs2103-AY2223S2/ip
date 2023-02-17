package kude.models;

import java.util.Optional;

/**
 * Representation of a Todo item
 */
public class Todo extends Task {
    public Todo(String content, Optional<String> place) {
        super(content, place);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
