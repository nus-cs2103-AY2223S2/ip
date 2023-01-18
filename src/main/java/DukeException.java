public abstract class DukeException extends Exception {
    protected final String DUKE_MESSAGE_PREFIX = ":( OOPS!!! ";

    public DukeException() {
        super();
    }

    public DukeException(String message) {
        super(message);
    }

    public String getDukeMessage() {
        return this.DUKE_MESSAGE_PREFIX + this.getMessage();
    }
}
