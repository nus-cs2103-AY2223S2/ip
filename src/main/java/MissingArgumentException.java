public class MissingArgumentException extends DukeException {
    private String command;
    public MissingArgumentException(String msg, String command) {
        super(msg);
        this.command = command;
    }
}