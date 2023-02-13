package duke.task;

/**
 * Represents a todo task.
 */
public class Todo extends Task {
    /**
     * Constructor for Todo.
     * 
     * @param description the description of the todo task.
     * @param isDone whether the todo task is done.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Formats the todo task for user output.
     * 
     * @return the string representation of the todo task for user output.
     */
    @Override
    public String toString() {
        return "[T] [" + this.getStatusIcon() + "] " + this.getDescription();
    }

    /**
     * Formats the todo task for file output.
     * 
     * @return the string representation of the todo task for file output.
     */
    public String toFile() {
        return "T | " + (this.isDone ? "1" : "0") + " | " + this.description;
    }
}
