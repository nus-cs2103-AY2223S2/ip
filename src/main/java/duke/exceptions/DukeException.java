package duke.exceptions;

/** An Exception class that informs user about a type of exception */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
