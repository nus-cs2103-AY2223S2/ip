package duke.exceptions;

public class InvalidException extends DukeException {
    public InvalidException() {
        super("☹ OOPS!!! I'm sorry, but your arguments are invalid!");
    }
}