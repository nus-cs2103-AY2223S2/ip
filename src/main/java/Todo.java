public class Todo extends Task {
    public Todo(String description) throws DukeException {
        super(description.trim());
        if (this.description.equals("")) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
    }
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
