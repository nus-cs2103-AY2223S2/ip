package duke;

public class ZeroLengthDescriptionException extends InvalidCommandException {
    public ZeroLengthDescriptionException() {
        super("The description of this task cannot be empty");
    }
}
