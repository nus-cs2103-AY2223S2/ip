package seedu.duke.exceptions;

public class DukeMarkOutOfBounds extends DukeException {

    public DukeMarkOutOfBounds(int index) {
        super("Sorry, that task (" + (index  + 1) + ") does not exist :(");
    }
}
