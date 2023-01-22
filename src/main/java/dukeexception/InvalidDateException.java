package dukeexception;

/**
<<<<<<< HEAD:src/main/java/dukeexception/InvalidDateException.java
 * Exception for handling incorrect date formats.
=======
 * Exception for handling invalid date formats.
>>>>>>> master:src/main/java/dukeException/InvalidDateException.java
 */
public class InvalidDateException extends DukeException {
    /**
     * Constructor for InvalidDateException.
     */
    public InvalidDateException() {
        super("The date wrong format. FAILURE!", null);
    }
}
