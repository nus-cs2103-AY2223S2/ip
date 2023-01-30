public class Todo extends Task {

    public Todo(String description) throws DukeException{
        super(description);
        if (description.isBlank()) {
            throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.\n");
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    @Override
    public String toFileString() {
        return String.format("T | %d | %s", isDone ? 1 : 0, description);
    }
}
