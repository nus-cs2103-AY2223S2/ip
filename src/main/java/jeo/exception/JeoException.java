package jeo.exception;

/**
 * Represents the custom exception that is thrown when an error is encountered while parsing user input.
 * @author Goh Jun How
 * @version 0.3
 */
public class JeoException extends Exception {

    /**
     * Creates a new custom exception with the specified error description.
     * @param e String describing the error message.
     */
    public JeoException(String e) {
        super(e);
    }

    /**
     * Gets the error message.
     * @return String representing the error message.
     */
    @Override
    public String getMessage() {
        return "[Error] " + super.getMessage();
    }
}
