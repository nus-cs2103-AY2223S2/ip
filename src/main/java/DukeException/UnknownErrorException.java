package DukeException;

public class UnknownErrorException extends DukeException {
    public UnknownErrorException() {}

    @Override
    public String toString() {
        return "Unknown error occurred\n";
    }
}
