package Exceptions;

public class EmptyDeadlineException extends DukeException {
    public EmptyDeadlineException(String e) {
        super("Hey! The description of a deadline cannot be empty!");
    }
}
