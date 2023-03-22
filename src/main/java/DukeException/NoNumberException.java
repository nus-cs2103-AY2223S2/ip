package DukeException;

public class NoNumberException extends DukeException {
    public NoNumberException() {}

    @Override
    public String toString() {
        return "Please key in number for this command\n";
    }
}
