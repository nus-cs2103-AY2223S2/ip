public class UnknownCommandException extends DukeException {
    private String command;

    public UnknownCommandException(String msg, String command) {
        super(msg);
        this.command = command;
    }
}