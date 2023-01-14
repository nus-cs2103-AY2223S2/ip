package SebastianExceptions;

public class InputFormatMismatchException extends IllegalArgumentException{
    public InputFormatMismatchException(String message) {
        super(message);
    }
}
