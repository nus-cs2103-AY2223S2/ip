package Exceptions;

public class DukeUnknownCommand extends DukeException {

    public DukeUnknownCommand() {
        super("Oh noes! I don't know what that means :<");
    }
}
