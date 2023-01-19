public class Todo extends Task {
    public Todo(String description) throws DukeException {
        super(description.trim());
        if (this.description.equals("")) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
    }
    /**
     * Represent Todo as a string
     * @return String representation of a Todo
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
