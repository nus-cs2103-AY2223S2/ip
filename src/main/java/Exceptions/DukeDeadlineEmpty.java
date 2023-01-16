package Exceptions;

public class DukeDeadlineEmpty extends DukeException {

    public DukeDeadlineEmpty() {
        super("Hey! The description of a deadline cannot be empty!");
    }
}
