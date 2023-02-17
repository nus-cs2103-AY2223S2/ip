package duke.exceptions;

public class DukeUnknownInputException extends DukeException {
    public DukeUnknownInputException() {
        super("I'm not sure I understand what that means.\nEnter the command help to see how to use the commands!");
    }
}
