package tigerlily.tasks;

public class ToDo extends Task {
    /**
     * Constructor for ToDo
     * @param description the description of the ToDo
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Generates the String representation of the ToDo needed for data storage.
     *
     * @return the ToDo in String format for data storage
     */
    @Override
    public String getStorageString() {
        return "T | " + super.getStorageString();
    }

    /**
     * Generates the String representation of the ToDo.
     *
     * @return the String representation of the ToDo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}