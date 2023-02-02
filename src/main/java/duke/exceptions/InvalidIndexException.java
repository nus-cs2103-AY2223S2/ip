package duke.exceptions;

public class InvalidIndexException extends DukeException{
    public InvalidIndexException() {
        super("Please input a number after the command.");
    }
}