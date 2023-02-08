package chattime.task;

/**
 * Represents task object of 'todo' type.
 */
public class Todo extends Task {

    /**
     * Creates Todo object with description given and parent constructor.
     *
     * @param description Todo task name.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Generates a data string of todo task to be stored in storage file.
     *
     * @return Data string of todo task.
     */
    @Override
    public String toDataString() {
        return TaskType.TODO + super.toDataString();
    }

    /**
     * Returns code and task name in a string.
     *
     * @return A string of code and task name for schedule use.
     */
    @Override
    public String taskWithCode() {
        return "[" + TaskType.TODO + "] " + description;
    }

    /**
     * Returns current data of todo task.
     *
     * @return Current situation of todo task.
     */
    @Override
    public String toString() {
        return "[" + TaskType.TODO + "]" + super.toString();
    }
}
