package duke.exception;

/**
 * Parent class for exceptions.
 */
public abstract class DukeException extends Exception {
    @Override
    public String toString() {
        return String.format("☹ OOPS!!!");
    }
}
