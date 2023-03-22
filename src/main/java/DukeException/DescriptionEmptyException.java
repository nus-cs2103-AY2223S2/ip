package DukeException;

public class DescriptionEmptyException extends DukeException {
    public DescriptionEmptyException() {}

    @Override
    public String toString() {
        return "OOPS!!! The description cannot be empty.\n";
    }
}
