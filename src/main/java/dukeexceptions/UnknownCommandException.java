package dukeexceptions;

public class UnknownCommandException extends DukeExceptions {
    public UnknownCommandException() {
        super("I'm sorry, but I don't know what that means :-(");
    }

}
