package duke;


/**
 * Type of task with just a description
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * Convert to the format to be saved in
     */
    @Override
    public String toSavedString() {
        String savedString = "T | " + super.toSavedString();
        return savedString;
    }

    /**
     * Convert to the format to be displayed in
     */
    @Override
    public String toString() {
        String outputString = "[T]" + super.toString();
        return outputString;
    }
}
