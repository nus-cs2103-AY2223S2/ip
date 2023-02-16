package genie.task;

/**
 * Deals with creating to do tasks.
 */
public class ToDo extends Task {
    /**
     * A constructor that takes in the task descriptor.
     * @param description of to do
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns to do as a formatted string to print for list command.                                                                                                                                                                                                          for list command
     * @return to do
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    /**
     * Returns to do as a formatted string to print after loading .txt file.
     * @return to do
     */
    @Override
    public String toFileFormat() {
        return "[T]" + super.toFileFormat();
    }
}
