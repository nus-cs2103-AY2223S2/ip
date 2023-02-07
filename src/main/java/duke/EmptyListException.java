package duke;

public class EmptyListException extends DukeException {
    public EmptyListException() {
        super("OOPS!!! The list cannot be empty when u do marking, unmarking or deleting.");
    }
}
