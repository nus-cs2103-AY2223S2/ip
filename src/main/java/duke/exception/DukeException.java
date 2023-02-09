package duke.exception;

/**
 *      File name: duke.exception.DukeException.java
 *      @author: Jerome Neo
 *      Description: duke.exception.DukeException inherits from Exception.
 */
public class DukeException extends Exception {
    /**
     * Constructor for exception. Takes in the message you want to include in this custom exception.
     *
     * @param message
     */
    public DukeException(String message) {
        super(message);
    }
}
