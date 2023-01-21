package book.exception;

import book.exception.BookException;

public class SaveException extends BookException {
    public SaveException(String message) {
        super(message);
    }
}
