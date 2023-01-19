public class InvalidTaskNumber extends DukeException {
    /**
     * Initialize an InvalidTaskNumber exception, which represents
     * the error that the task number entered is not valid.
     *
     * @return A InvalidTaskNumber exception
     */
    public InvalidTaskNumber() {
        super("OOOPS! The task number is not available. Try another number!");
    }
}
