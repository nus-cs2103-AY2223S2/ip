package duke;

/**
 * Exception thrown for invalid user input
 */
public class UnknownCommandException extends DukeException {

    /**
     * Constructor for new UnknownCommandException
     */
    public UnknownCommandException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * String representation of UnknownCommandException
     * 
     * @return string for message for UnknownCommandException
     */
    @Override
    public String toString() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
