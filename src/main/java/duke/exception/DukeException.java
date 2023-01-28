package duke.exception;

/** Exception for problems that arise when running Duke */
public class DukeException extends Exception {

    public DukeException(String message) {
        super("I'm sorry. " + message);
    }

}
