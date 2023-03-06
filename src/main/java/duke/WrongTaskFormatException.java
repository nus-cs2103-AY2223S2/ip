package duke;

/**
 * Exception thrown if any of the Strings input to Tasks are in the wrong format
 */
public class WrongTaskFormatException extends Exception {
    /**
     * Returns a WrongTaskFormatException with errorMessage stored
     *
     * @param errorMessage String of the error message
     */
    public WrongTaskFormatException(String errorMessage) {
        super(errorMessage);
    }
}