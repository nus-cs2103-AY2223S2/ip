package duke.exceptions;

import java.lang.Exception;
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}

