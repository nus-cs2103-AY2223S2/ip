package duke.exception;

public class InvalidArgumentException extends DukeException {
    private String command;

    public InvalidArgumentException(String command) {
        super("Arguments are of the wrong type or format");
        this.command = command;
    }

    @Override
    public String getExceptionName() {
        String name = String.format("Invalid Argument(s) for %s", this.command);
        return name;
    }
}
