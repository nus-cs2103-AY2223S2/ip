package Exceptions;

import Exceptions.DukeException;

public class IncompleteCommandException extends DukeException {
    public IncompleteCommandException(String errorMessage, Throwable error) {
        super(errorMessage,error);
    }
}
