package task;

/**
 * Todo class that inherits from Task.
 */
public class Todo extends Task {


    /**
     * Construct todo.
     *
     * @param name Description of the task.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Returns the details of the todo task to be written in file.
     *
     * @return Details of todo task.
     */
    public String toText() {

        return "T" + "|" + getDescription() + "|" + (isDone() ? 1 : 0);
    }

    /**
     * Returns the details of the todo task to be output to user.
     *
     * @return Details of todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
