package book.exception;

import book.exception.BookException;

public class LoadException extends BookException {
    public LoadException(String message) {
        super(message);
    }
}
