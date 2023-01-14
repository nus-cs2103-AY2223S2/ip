package dukeexeption;

public class UnknownCommandException extends DukeException {
    public UnknownCommandException() {
        super("I'm sorry, but I don't know what that means :-(");
    }
}
