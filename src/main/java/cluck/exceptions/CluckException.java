package cluck.exceptions;

/**
 * Cluck exception is the parent class of all checked exceptions regarding interactions with Cluck!.
 */
public class CluckException extends Exception {
    /**
     * Instantiates a new Cluck exception.
     *
     * @param errorMessage The error message.
     */
    public CluckException(String errorMessage) {
        super(errorMessage);
    }
}
