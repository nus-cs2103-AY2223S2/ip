package duke.exception.blankfieldexceptions;

/**
 *  Thrown when an event-related command has a blank field.
 */
public class BlankFieldEventException extends BlankFieldException {
    /**
     *  Thrown when an event-related command has a blank field.
     */
    public BlankFieldEventException() {
        super("\n"
                + "     ☹ OOPS!!! The description, start date or end date of a event cannot be empty."
                + "\n");
    }
}
