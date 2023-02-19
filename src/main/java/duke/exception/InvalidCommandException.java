package duke.exception;

/**
 * A class to represent an invalid command.
 */
public class InvalidCommandException extends Error {

    public InvalidCommandException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
