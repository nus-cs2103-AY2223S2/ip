package task;
/**
 * ToDo is a type of Task.
 * This is the most primitive version of Task
 * @author EL
 */
public class ToDo extends Task {

    /**
     * This method constructs a ToDo task with the given name/description and status.
     *
     * @param description The name of this Todo task.
     * @param isComplete The status of this task.
     */
    public ToDo(String description, boolean isComplete) {
        super(description, isComplete);
    }

    /**
     * This method returns the String representation of the Todo task.
     * @return The string representation of this Todo.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    /**
     * This method returns the String representation of the Todo task delimited by commas.
     *
     * @return The string representation of this Todo task in CSV format.
     */
    @Override
    public String toCsv() {
        return String.format("T,%s,%s", this.getTaskDescription(), this.getComplete());
    }
}
