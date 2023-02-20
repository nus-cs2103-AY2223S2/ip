package chagee.exception;

/**
 * A class to represent an invalid command exception.
 */
public class InvalidCommandException extends Error {

    public InvalidCommandException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
