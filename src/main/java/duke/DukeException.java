package duke;

/**
 * Exception that will be thrown in the duke program
 *
 * @author Cheam Jia Wei
 */
public class DukeException extends Exception {
    private String message;

    public DukeException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
