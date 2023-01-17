public class NoDescriptionException extends DukeException {
    public NoDescriptionException(String task, Throwable err) {
        super("OOPS!!! The description of a " + task + " cannot be empty.", err);
    }
}
