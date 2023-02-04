package duke;

public class DescriptionEmptyException extends DukeException {
    DescriptionEmptyException() {}

    @Override
    public String toString() {
        return "OOPS!!! The description cannot be empty.\n";
    }
}
