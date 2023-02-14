package dukeexception.commandexception;

import dukeexception.DukeException;

/**
 * Exception for handling incorrect input format for user commands.
 */
public class InputFormatException extends DukeException {
    /**
     * Constructor for InputFormatException.
     * @param source Where the exception is raised.
     * @param reason Why the exception is raised.
     * @param err Throwable to pass to parent constructor.
     */
    public InputFormatException(String source, String reason, Throwable err) {
        super(String.format("Haiya wrong format. You **** up at %s because %s.", source, reason), err);
    }
}
