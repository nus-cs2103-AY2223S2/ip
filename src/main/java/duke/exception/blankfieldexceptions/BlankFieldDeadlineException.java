package duke.exception.blankfieldexceptions;

public class BlankFieldDeadlineException extends BlankFieldException{
    public BlankFieldDeadlineException() {
        super("\n"
                + "     ☹ OOPS!!! The description or date of a deadline cannot be empty."
                + "\n");
    }
}
