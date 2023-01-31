package duke.exception;

public class UnknownCommandError extends DukeException {
    public UnknownCommandError(String err) {
        super("\n" + "    ____________________________________________________________\n" +
                "Sorry I don't think there's a command like that!" + "\n" +
                "    ____________________________________________________________\n");
    }
}
