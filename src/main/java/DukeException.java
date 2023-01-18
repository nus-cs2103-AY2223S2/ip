/**
 * This class represents an exception related to the Duke chatbot.
 *
 * @version CS2103T AY22/23 Sem 2 Individual Project
 * @author A0233828Y Eugene Tang
 */
public class DukeException extends Exception {

    /**
     * Constructs an exception message indicating the reason for exception.
     * @param exceptionMessage Message to be displayed when the exception occurs
     */
    public DukeException(String exceptionMessage) {
        super(exceptionMessage);
    }

}
