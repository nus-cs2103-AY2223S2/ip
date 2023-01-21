package book.exception;

import book.exception.BookException;

public class IncompleteInputException extends BookException {
    public IncompleteInputException(String message) {
        super(message);
    }
}
