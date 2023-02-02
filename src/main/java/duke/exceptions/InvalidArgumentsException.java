package duke.exceptions;

public class InvalidArgumentsException extends DukeException{
    public InvalidArgumentsException() {
        super("Sorry, I don't understand what you have wrote after the command");
    }
}