package duke.exceptions;

/**
 * Represents an exception that occurs when Duke does not understand the input.
 * @author pzhengze
 */
public class DukeUnknownCommandException extends DukeException {
    /**
     * Constructor for DukeUnknownCommandException.
     */
    public DukeUnknownCommandException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
