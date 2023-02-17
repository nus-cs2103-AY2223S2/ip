package duke.util.parser;

import duke.common.exception.DukeRuntimeException;

/**
 * Exception that occurs when a string input cannot be parsed.
 */
public class ParserException extends DukeRuntimeException {

    /**
     * Constructs a default instance.
     */
    public ParserException() {}

    /**
     * Constructs an instance with a specific error message.
     *
     * @param message the error message
     */
    public ParserException(String message) {
        super(message);
    }
}
