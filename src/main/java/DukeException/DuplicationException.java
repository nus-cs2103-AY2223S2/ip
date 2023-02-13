package DukeException;

public class DuplicationException extends DukeException {
    public DuplicationException() {}

    @Override
    public String toString() {
        return "Sorry, this task is already added in your list.";
    }

}
