package duke.exceptions;

public class InvalidSyntaxException extends DukeException {
    /**
     * Initialize an duke.exceptions.InvalidSyntaxException exception, which represents
     * the error that the syntax is not correct.
     *
     * @return A duke.exceptions.InvalidSyntaxException exception
     */
    public InvalidSyntaxException() {
        super("OOPS! Invalid syntax here, double check your spelling");
    }
}
