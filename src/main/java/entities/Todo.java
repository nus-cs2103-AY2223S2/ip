package entities;

import enums.TaskType;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public SerializableTask serialize() {
        return new SerializableTask(TaskType.TODO, isDone, description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
