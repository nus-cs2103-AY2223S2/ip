package duke.exceptions;

public abstract class DukeException extends Exception {
    protected final static String DUKE_MESSAGE_PREFIX = ":( OOPS!!! ";

    public DukeException() {
        super();
    }

    public DukeException(String message) {
        super(message);
    }

    public String getDukeMessage() {
        return DukeException.DUKE_MESSAGE_PREFIX + this.getMessage();
    }
}
