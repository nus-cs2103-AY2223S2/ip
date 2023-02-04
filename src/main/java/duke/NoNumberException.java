package duke;

public class NoNumberException extends DukeException {
    NoNumberException() {}

    @Override
    public String toString() {
        return "Please key in number for this command\n";
    }
}
