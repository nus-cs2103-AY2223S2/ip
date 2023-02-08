package duke;

public class Todo extends Task {

    public Todo(String description) throws DukeException {
        super(description);
        if (description.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }

    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
