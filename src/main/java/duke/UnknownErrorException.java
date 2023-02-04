package duke;

public class UnknownErrorException extends DukeException {
    UnknownErrorException() {}

    @Override
    public String toString() {
        return "Unknown error occurred\n";
    }
}
