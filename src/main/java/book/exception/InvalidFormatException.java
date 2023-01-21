package book.exception;

import book.exception.BookException;

public class InvalidFormatException extends BookException {
    public InvalidFormatException(String message) {
        super(message);
    }
}
