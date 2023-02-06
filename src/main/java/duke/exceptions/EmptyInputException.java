package duke.exceptions;

/**
 * The exception class that informs a user that the input was empty.
 *
 * @author JamesLiuZX
 *     AY2223-S2 CS2103T
 */
public class EmptyInputException extends DukeException {
    /**
     * The default constructor for this exception.
     */
    public EmptyInputException() {
        super("The input cannot be empty.");
    }
}
