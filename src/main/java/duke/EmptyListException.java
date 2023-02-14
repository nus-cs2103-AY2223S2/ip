package duke;

public class EmptyListException extends DukeException {
    /**
     * Constructor
     */
    public EmptyListException() {
        super("OOPS!!! The list cannot be empty when u do marking, unmarking, deleting or finding.");
    }
}
