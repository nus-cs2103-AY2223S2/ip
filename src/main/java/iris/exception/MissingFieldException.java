package src.main.java.exception;

public class MissingFieldException extends IrisException {
    public MissingFieldException(String field) {
        super(field + " cannot be empty.");
    }
}
