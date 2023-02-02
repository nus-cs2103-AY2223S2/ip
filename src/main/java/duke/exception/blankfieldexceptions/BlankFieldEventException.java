package duke.exception.blankfieldexceptions;

public class BlankFieldEventException extends BlankFieldException {
    public BlankFieldEventException() {
        super("\n"
                + "     ☹ OOPS!!! The description, start date or end date of a event cannot be empty."
                + "\n");
    }
}
