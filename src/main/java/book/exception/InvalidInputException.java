package book.exception;

import book.exception.BookException;

public class InvalidInputException extends BookException {
    public InvalidInputException(String message) {
        super(message);
    }
}
