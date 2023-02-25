package duke;

public class UnknownCommandException extends DukeException{

    public UnknownCommandException() {
        super("i don't know that command.\n");
    }
}
