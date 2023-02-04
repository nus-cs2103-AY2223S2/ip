package duke;

public class UnclearCommandException extends DukeException {
    UnclearCommandException() {}

    @Override
    public String toString() {
        return "OOPS!!! I'm sorry, but I don't know what that means.\n";
    }
}
