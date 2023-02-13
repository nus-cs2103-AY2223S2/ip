package Exceptions;

public class IncompleteInputException extends MunchException {
    public IncompleteInputException() {
        super("Bro, this is not a complete command!");
    }
}
