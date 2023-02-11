package vic.exceptions;
/**
 * An abstract exception class which is super
 * class for all check exception for duke program
 */
public class DukeException extends Exception {
    /**
     * Constructor for DukeException
     *
     * @param errorMessage the error message for exception
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
