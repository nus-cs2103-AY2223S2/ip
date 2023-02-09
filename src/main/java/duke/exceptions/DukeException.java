package duke.exceptions;

/**
 * DukeException class that handles exceptions that Duke has.
 */
public class DukeException extends Exception {

    /**
     * duke.exceptions.DukeException constructor. passes on errorMessage into superclass.
     *
     * @param errorMessage The description of the duke exception.
     */
    public DukeException(String errorMessage) {

        super(errorMessage);
    }
}
