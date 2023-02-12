package tasks;


public class Todo extends Task {

    /**
     * Constructor for Todo.
     * 
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the status icon of the task.
     * 
     * @return The status icon of the task.
     */
    @Override
    public String toString() {
        return "[T]" + "[" + this.getStatusIcon() + "] " + this.getDescription() + " ";
    }

    /**
     * Returns the description of the task.
     * 
     * @return The description of the task.
     */
    @Override
    public String writeToFile() {
        return "[T]" + "[" + this.getStatusIcon() + "] " + this.getDescription() + " ";
    }
}
