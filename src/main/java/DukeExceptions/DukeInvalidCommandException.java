package DukeExceptions;

public class DukeInvalidCommandException extends DukeException{
    public DukeInvalidCommandException() {
        super("I don't know that command yet. Give me another!");
    }
}
