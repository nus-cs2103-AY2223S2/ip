package duke;

public class InvalidActionInput extends DukeException {
    public InvalidActionInput() {
        super("OOPS!!! Invalid input ><\nInput should be: <action> <number>");
    }
}
