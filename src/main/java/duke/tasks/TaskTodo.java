package duke.tasks;

/**
 * A task with no specified starting/ending times.
 */
public class TaskTodo extends Task {
    public TaskTodo(String description) {
        super(description);
    }

    /**
     * Parses a todo task that has been encoded into a string, into a 'TaskTodo'
     * instance.
     * 
     * @param input The encoded todo task.
     * @return The todo task that was encoded.
     * @throws DukeSaveLoadException If there's a problem in parsing the encoded
     *         task.
     */
    public static TaskTodo loadFromString(String input) {
        String[] values = Task.decodeValues(input);
        boolean isDone = values[1].equals("1");
        String description = values[2];

        TaskTodo output = new TaskTodo(description);
        if (isDone) {
            output.markAsDone();
        }
        return output;
    }

    @Override
    public String encodeAsString() {
        return Task.encodeValues(new String[] {
            "T",
            isDone ? "1" : "0",
            description });
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof TaskTodo)) {
            return false;
        }
        return true;
    }
}
