package dukeexception.commandexception;

import dukeexception.DukeException;

/**
 * Exception for handling an incorrect input format of a user response.
 */
public class InputFormatException extends DukeException {
    /**
     * Constructor for InputFormatException
     * @param source Where the exception is raised.
     * @param reason Why the exception is raised.
     * @param err Error throwable to be passed to parent constructor.
     */
    public InputFormatException(String source, String reason, Throwable err) {
        super("Haiya input format wrong. \nHere is where you mess up: "
                + source + "\nHere is why you wrong: " + reason, err);
    }
}
