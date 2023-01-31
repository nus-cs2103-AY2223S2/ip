package duke.exception;

public class DukeInvalidCommandException extends DukeException {

    public DukeInvalidCommandException() {
        super("Hmm, your command format's a little off!");
    }
    public DukeInvalidCommandException(String msg) {
        super(msg);
    }

}
