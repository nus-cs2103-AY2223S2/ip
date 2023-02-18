package duke.exception.blankfieldexceptions;

/**
 *  Thrown when a to-do-related command has a blank field.
 */
public class BlankFieldTodoException extends BlankFieldException {
    /**
     *  Thrown when a to-do-related command has a blank field.
     */
    public BlankFieldTodoException() {
        super("\n"
                + "     ☹ OOPS!!! The description of a todo cannot be empty."
                + "\n");
    }
}
