package duke.exceptions;

public class InputDukeException extends DukeException {
    public InputDukeException() {
        super("Not enough details are given!\n The duke.Duke expects more information!");
    }
}
