package duke.entities;

import java.time.LocalDate;

import duke.enums.TaskType;

/**
 * Represents the Todo task.
 */
public class Todo extends Task {

    /**
     * Instantiates a todo object.
     *
     * @param description the description of the task
     */
    public Todo(String description) {
        super(description);
        this.taskType = TaskType.TODO;
    }

    /**
     * Serializes the task.
     *
     * @return Returns serialized representation.
     */
    @Override
    public SerializableTask serialize() {
        return new SerializableTask(TaskType.TODO, isDone, description);
    }

    @Override
    public boolean isActiveOn(LocalDate date) {
        return false;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
