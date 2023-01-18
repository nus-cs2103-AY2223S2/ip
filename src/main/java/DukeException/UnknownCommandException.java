package DukeException;

public class UnknownCommandException extends DukeException {

    /**
     * Constructor to create an exception for unknown command
     */
    public UnknownCommandException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
