package duke.tasks;

import duke.exceptions.DukeInvalidInputException;

public class Todo extends Task {
    private Todo(String name) {
        super(name);
    }

    public static Todo createTodo(String input) throws DukeInvalidInputException {
        String sanitisedInput  = input.trim();
        if (input.isEmpty() || sanitisedInput.isEmpty()) {
            throw new DukeInvalidInputException("Todos need a description");
        }
        return new Todo(sanitisedInput);
    }
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String toText() {
        return String.format("T %d %s\n", super.isDone ? 1 : 0, super.name);
    }
}
