package duke.exception;

/**
 * Custom Exceptions specific to the 'duke.Duke' class where duke.Duke does not
 * understand the given input.
 *
 * @author Bo Kuan (LG17)
 * @version CS2103T AY22/23 Semester 2
 */

public class DukeUnknownInputException extends DukeException {

    /**
     * Constructor for a duke.exception.DukeUnknownInputException.
     * @param message The error message.
     */
    public DukeUnknownInputException(String message) {
        super(message);
    }

    /**
     * Returns the string representation of a duke.exception.DukeUnknownInputException.
     *
     * @return The string representation of a duke.exception.DukeUnknownInputException.
     */
    @Override
    public String toString() {
        return "\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
