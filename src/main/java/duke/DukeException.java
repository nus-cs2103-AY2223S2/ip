package duke;

/**
 * DukeException class that handles exceptions that Duke has.
 */
public class DukeException extends Exception {

    /**
     * duke.DukeException constructor. passes on errorMessage into superclass.
     *
     * @param errorMessage The description of the duke exception.
     */
    public DukeException(String errorMessage) {

        super(errorMessage);
    }

//    /**
//     * Returns string representation of the duke exception.
//     *
//     * @return The string of the duke exception.
//     */
//    @Override
//    public String toString() {
//
//        return "Duke Exception: OOPS! " + super.getMessage();
//    }
}
