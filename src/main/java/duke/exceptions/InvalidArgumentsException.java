package duke.exceptions;

/**
 * The exception class that indicates that the input arguments after the command are invalid or not enough.
 *
 * @author JamesLiuZX
 *     AY2223-S2 CS2103T

 */
public class InvalidArgumentsException extends DukeException {
    /**
     * The default constructor for this exception.
     */
    public InvalidArgumentsException() {
        super("Sorry, I don't understand what you have wrote after the command");
    }
}