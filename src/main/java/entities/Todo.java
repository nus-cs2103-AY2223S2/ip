package entities;

import entities.Task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String toSave() {
        if (super.isDone == true) {
            return String.format("T | 1 | %s\n", super.getDescription());
        }
        return String.format("T | 0 | %s\n", super.getDescription());
    }
}
