package duke;

import duke.DukeException;

public class UnknownInputException extends DukeException {
    public UnknownInputException(String message){
        super(message);
    }
}
