package yj;

public class ToDo extends Task {

    public ToDo(String description) throws IllegalArgumentException {
        super(description);
        if (description.isEmpty()) {
            throw new IllegalArgumentException("Crapadoodle! the description of a todo cannot be empty.");
        }
    }

    @Override
    public String toString() {
        // [T][X] read book
        return "[T]" + " " + super.getStatusIcon() + " " + super.toString();
    }

    /**
     * Returns the string representation of the todo to be saved in the file.
     *
     * @return string representation of the todo to be saved in the file.
     */
    @Override
    public String toSaveString() {
        // D | 0 | return book | June 6th
        return "T | " + (isDone ? 1 : 0) + " | " + super.toString();
    }
}
