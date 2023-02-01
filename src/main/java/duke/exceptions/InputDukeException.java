package duke.exceptions;

public class InputDukeException extends DukeException {
    public InputDukeException() {
        super("Not enough details are given!\nThe Duke expects more information!");
    }
}
