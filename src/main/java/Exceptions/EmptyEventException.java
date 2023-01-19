package Exceptions;

public class EmptyEventException extends DukeException {
    public EmptyEventException(String e) {
        super("Hey! The description of an event cannot be empty!");
    }
}
