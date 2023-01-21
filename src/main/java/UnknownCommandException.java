public class UnknownCommandException extends DukeException {
    private String command;

    public UnknownCommandException(String command) {
        super("I'm sorry, but I don't know what that means :-(");
        this.command = command;
    }
}