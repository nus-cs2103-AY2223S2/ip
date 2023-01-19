public class MissingTimeException extends DukeException {
    /**
     * Initialize a MissingTimeException exception, which represents
     * the error that the time field is missing
     *
     * @return A MissingTimeException exception
     */
    public MissingTimeException() {
        super("OOPS! The time element is missing.");
    }
}
