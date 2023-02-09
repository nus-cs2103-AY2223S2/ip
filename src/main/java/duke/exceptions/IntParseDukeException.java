package duke.exceptions;

/**
 * A class that represents errors created from saving operations while Duke is shutting down.
 */
public class IntParseDukeException extends DukeException {
    public IntParseDukeException() {
        super("It seems like you have provided the Duke with the wrong values!\n" +
                "Maybe try inputting the value as a number instead?");
    }
}
