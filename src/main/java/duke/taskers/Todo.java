package duke.taskers;

public class Todo extends Task {

    /**
     * constructor of todo
     * @param description description of what the todo task is
     * @param isDone true if todo is done, false if todo is not done
     */
    public Todo(String description,  boolean isDone) {
        super(description, isDone);
    }

    /**
     * formats the string before being added to the duke storage file
     * @return the formatted string to be added to the duke storage file
     */
    public String statusStringForFile() {
        return String.format("TODO / %s", super.stringFormatForFile());
    }

    /**
     * string representation of the todo
     * @return todo string
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
