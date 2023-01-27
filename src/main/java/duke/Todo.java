package duke;

public class Todo extends Task {

    /**
     * Constructor of todo.
     *
     * @param description Description of what the todo task is.
     * @param isDone True if todo is done, false if todo is not done.
     */
    public Todo(String description,  boolean isDone) {

        super(description, isDone);
    }

    /**
     * Formats the string before being added to the duke storage file.
     *
     * @return The formatted string to be added to the duke storage file.
     */

    public String formatStringForFile() {
        return String.format("TODO / %s",
                super.helpFormatString());
    }

    /**
     * String representation of the todo.
     *
     * @return duke.Todo string.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
