package Exceptions;

import Exceptions.DukeException;

public class InvalidIndexException extends DukeException {
    public InvalidIndexException(String errorMessage, Throwable error) {
        super(errorMessage,error);
    }
}