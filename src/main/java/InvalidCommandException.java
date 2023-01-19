public class InvalidCommandException extends DukeException {
    /**
     * Initialize an InvalidCommandException exception, which represents
     * the error that the command is not valid.
     *
     * @return A InvalidCommandException exception
     */
    public InvalidCommandException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-( Double check your spellings or spaces");
    }
}
