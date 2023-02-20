package tasks;

import static tasks.Priority.LOW;
import static tasks.TaskType.TODO;

/**
 * Represents a simple to-do task
 */
public class Todo extends Task {
    /**
     * Constructs a Todo with the given description with
     * a default priority of LOW
     *
     * @param description the description of the to-do task
     */
    public Todo(String description) {
        super(description, TODO, LOW);
    }

    /**
     * Constructs a Todo with the given description and priority.
     *
     * @param description the description of the to-do task
     * @param priority the priority of the todo task
     */
    public Todo(String description, Priority priority) {
        super(description, TODO, priority);
    }

    /**
     * Constructs a Todo with the given description, status and priority.
     *
     * @param description the description of the to-do task
     * @param isDone the status of the to-do task (completed or not)
     * @param priority the priority of the to-do task
     */
    public Todo(String description, boolean isDone, Priority priority) {
        super(description, isDone, TODO, priority);
    }

    @Override
    public String toString() {
        return "[" + type.getValue() + "]" + super.toString();
    }

    @Override
    public String parseToSave() {
        return type.getValue() + " | " + super.parseToSave();
    }
}
