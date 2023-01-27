package jeo.exception;

/**
 * Custom jeo.exception that is thrown when there is a command error
 */
public class JeoException extends Exception {

    /**
     * Creates a new jeo.exception with description
     * @param e Describes the error message
     */
    public JeoException(String e) {
        super(e);
    }

    /**
     * Gets the error message
     * @return error message
     */
    @Override
    public String getMessage() {
        return "[Error] " + super.getMessage();
    }
}
