package duke.exceptions;

/**
 * A class that represents errors created from input-related operations while Duke is running.
 */
public class InputDukeException extends DukeException {
    public InputDukeException() {
        super("Not enough details are given!\nThe Duke expects more information!");
    }
}
