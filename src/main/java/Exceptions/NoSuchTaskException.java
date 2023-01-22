package Exceptions;

import Exceptions.DukeException;

public class NoSuchTaskException extends DukeException {
    public NoSuchTaskException(String errorMessage, Throwable error) {
        super(errorMessage,error);
    }
}
