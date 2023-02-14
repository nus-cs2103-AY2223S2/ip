package duke.task;

/**
 * Represents a to-do task.
 */
public class ToDo extends Task{
    /**
     * @param description the description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * @return the string representation of the task to be stored in the hard disk.
     */
    public String toStorageString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    //...
}
