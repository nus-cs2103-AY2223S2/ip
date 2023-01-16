package Exceptions;

public class DukeEventEmpty extends DukeException {
    public DukeEventEmpty() {
        super("Hey! The description of an event cannot be empty!");
    }
}
