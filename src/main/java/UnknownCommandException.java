public class UnknownCommandException extends DukeException {
    private String command;

    public UnknownCommandException(String command) {
        super(String.format("I'm sorry, but I don't know what <%s> means :-(", command));
        this.command = command;
    }
}