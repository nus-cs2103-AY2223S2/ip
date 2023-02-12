package duke.exceptions;

/**
 * The exception class that indicates that too many arguments were entered after the command.
 *
 * @author JamesLiuZX
 *     AY2223-S2 CS2103T

 */
public class MultipleArgumentsException extends DukeException{
    /**
     * The default constructor for this exception.
     */
    public MultipleArgumentsException() {
        super("Too many inputs after the command!");
    }
}
