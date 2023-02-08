package duke.task;

/**
 * A simple todo task.
 */
public class Todo extends Task {

    /**
     * Constructs a new task.
     *
     * @param description Description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the command(s) required to recreate the todo.
     *
     * @param id Identifier of the task, usually assigned by task list.
     * @return the command(s).
     */
    @Override
    public String getRecreateCommand(int id) {
        String result = "todo " + description;
        if (isDone) {
            result += "\nmark " + id;
        }
        return result;
    }

    /**
     * Returns the string representation of the todo.
     *
     * @return String representation of the todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }


    /**
     * Returns whether this todo have the same description as another todo.
     *
     * @param other Other object.
     * @return true if other object is a todo and has the same description,
     *         false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return super.equals(other) && (other instanceof Todo);
    }
}
