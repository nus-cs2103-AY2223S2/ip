package duke.exception.blankfieldexceptions;

/**
 *  Thrown when a deadline-related command has a blank field.
 */
public class BlankFieldDeadlineException extends BlankFieldException {
    /**
     *  Thrown when a deadline-related command has a blank field.
     */
    public BlankFieldDeadlineException() {
        super("\n"
                + "     ☹ OOPS!!! The description or date of a deadline cannot be empty."
                + "\n");
    }
}
