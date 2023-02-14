package duke.exceptions;

/**
 * Parent of all other exceptions, thrown in general cases
 */
public class NeroException extends Exception {

    public NeroException(String errorMessage) {
        super(errorMessage);
    }
}
