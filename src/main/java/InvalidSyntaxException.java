public class InvalidSyntaxException extends DukeException{
    /**
     * Initialize an InvalidSyntaxException exception, which represents
     * the error that the syntax is not correct.
     *
     * @return A InvalidSyntaxException exception
     */
    public InvalidSyntaxException() {
        super("OOPS! Invalid syntax here, double check your spelling");
    }
}
