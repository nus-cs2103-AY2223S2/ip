/**
 * Encapsulation of exception specific to Duke.
 */
public class DukeException extends Exception{
    /**
     * Constructor for DukeException.
     * @param message The error message.
     */
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return ("OPPS! " + super.getMessage() + "\nPlease try again.\n");
    }
}
