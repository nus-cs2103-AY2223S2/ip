package entities;

import enums.TaskType;

import java.time.LocalDate;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public SerializableTask serialize() {
        return new SerializableTask(TaskType.TODO, isDone, description);
    }

    @Override
    public boolean activeOn(LocalDate date) {
        return false;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
