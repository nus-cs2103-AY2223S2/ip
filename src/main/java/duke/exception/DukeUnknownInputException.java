package duke.exception;

/**
 * If the user inputs a <code>Command</code> that Duke does not understand, then a
 * <code>DukeUnknownInputException</code> will be thrown.
 *
 * @author Bo Kuan (LG17)
 * @version CS2103T AY22/23 Semester 2
 */
public class DukeUnknownInputException extends DukeException {
    /**
     * Constructor for a <code>DukeUnknownInputException</code>.
     * @param message The error message.
     */
    public DukeUnknownInputException(String message) {
        super(message);
    }
    /**
     * Returns the string representation of a <code>DukeUnknownInputException</code>.
     *
     * @return The string representation of a <code>DukeUnknownInputException</code>.
     */
    @Override
    public String toString() {
        return "\t:( OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
