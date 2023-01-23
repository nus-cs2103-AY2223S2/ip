/**
 * Represents an exception related to the Duke chatbot.
 */
public class DukeException extends Exception {

    /**
     * Constructs an exception message indicating the reason for exception.
     *
     * @param exceptionMessage Message to be displayed when the exception occurs
     */
    public DukeException(String exceptionMessage) {
        super(exceptionMessage);
    }

}
