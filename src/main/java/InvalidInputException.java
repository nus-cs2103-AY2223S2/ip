public class InvalidInputException extends DukeException {
    public InvalidInputException(Throwable err) {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(", err);
    }
}
