package duke;
import java.io.FileNotFoundException;

/**
 * handles abstraction
 */
public class DukeException extends Exception {

    public DukeException() {
        super("idk what you are saying");
    }
    public DukeException(String problem) {
        super(problem);
    }
    public DukeException(Throwable cause) {
        super(cause.toString());
    }
    public DukeException(IllegalArgumentException i) {
        super("did you forget a keyword? ie /by /to /from");
    }
    public DukeException(FileNotFoundException i) {
        super("File not found");
    }
    public DukeException(IndexOutOfBoundsException i) {
        super("Did you try to mark a task that is not in the list?");
    }
    public DukeException(NumberFormatException i) {
        super("You must input a number");
    }
    public String toString() {
        return String.format(super.getMessage());
    }
}
