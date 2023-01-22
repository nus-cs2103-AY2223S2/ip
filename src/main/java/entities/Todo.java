package entities;

import java.time.LocalDate;

import enums.TaskType;

/**
 * Represents the Todo task.
 */
public class Todo extends Task {

    /**
     * Instantiate a todo object.
     *
     * @param description the description of the task
     */
    public Todo(String description) {
        super(description);
        this.taskType = TaskType.TODO;
    }

    /**
     * Serialize the task.
     *
     * @return Returns serialized representation.
     */
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
