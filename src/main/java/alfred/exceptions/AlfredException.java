package alfred.exceptions;

/**
 * Represents exceptions that are caused by the Alfred program.
 */
public class AlfredException extends Exception {

    /**
     * Constructs an Alfred Exception given the error message.
     * @param message The error message.
     */
    public AlfredException(String message) {
        super("ERROR! " + message);
    }

    /**
     * Converts the task into a String which represents a readable format for the user.
     * @return The readable format of the task.
     */
    @Override
    public String toString() {
        return this.getMessage();
    }
}
