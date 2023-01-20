package duke;

import duke.DukeException;

public class EmptyArgException extends DukeException {
    public EmptyArgException(String message){
        super(message);
    }
}