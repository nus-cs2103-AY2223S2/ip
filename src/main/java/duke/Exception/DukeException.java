package duke.Exception;

/**
 * The abstraction behind each exception thrown by duke.Utilities.Duke.
 */
public abstract class DukeException extends Exception {

    /**
     * The constructor for a duke.Utilities.Duke exception.
     * @param message The error message.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Getter for the type of exception thrown.
     * @return The type of exception.
     */
    public abstract String getExceptionType();

    @Override
    public String toString() {
        return this.getExceptionType() + " detected! " + super.getMessage();
    }
}
