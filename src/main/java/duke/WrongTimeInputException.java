package duke;

public class WrongTimeInputException extends DukeException {
    public WrongTimeInputException() {
        super("Sorry your input format for the deadline task is wrong :(");
    }
}
