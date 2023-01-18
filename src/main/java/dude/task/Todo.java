package dude.task;

import dude.task.Task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toRaw() {
        return "T | " + (this.isDone ? 1 : 0) + " | " + description + "\n";
    }
}
