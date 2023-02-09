package duke;

public class Todo extends Task {
    /**
     * Constructor method for Todo
     * @param description task's description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Overridden method for print Todo task
     * @return String output of the task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Formats the task's details for saving to file
     * @return formatted task's details
     */
    @Override
    public String formatForFile() {
        return "T | " + (this.isDone ? 1 : 0) + " | " + this.description + "\n";
    }
}