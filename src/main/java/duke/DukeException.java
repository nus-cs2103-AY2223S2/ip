package duke;

/**
 * This class is for exceptions specific to the chatbot.
 *
 * @author wz2k
 */
public class DukeException extends Exception{
    /**
     * Constructor for duke.DukeException class.
     *
     * @param message the error message.
     */
    public DukeException(String message) {
        super(message);
    }
}
