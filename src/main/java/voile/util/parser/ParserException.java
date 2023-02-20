package voile.util.parser;

import voile.common.exception.VoileRuntimeException;

/**
 * Exception that occurs when a string input cannot be parsed.
 */
public class ParserException extends VoileRuntimeException {

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
