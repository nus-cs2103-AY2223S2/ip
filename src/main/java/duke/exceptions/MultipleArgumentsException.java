package duke.exceptions;

public class MultipleArgumentsException extends DukeException{
    public MultipleArgumentsException() {
        super("Too many inputs after the command!");
    }
}