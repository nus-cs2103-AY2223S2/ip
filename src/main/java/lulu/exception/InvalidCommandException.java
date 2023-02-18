package lulu.exception;

/**
 * Represents an exception to be thrown, when the Command given by the user is invalid.
 */
public class InvalidCommandException extends LuluException {
    public InvalidCommandException() {
        super("That's not a valid command.");
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
