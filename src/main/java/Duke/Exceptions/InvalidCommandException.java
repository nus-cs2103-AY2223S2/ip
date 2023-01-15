package Duke.Exceptions;

public class InvalidCommandException extends Exception {
    public InvalidCommandException() {
        super(String.format("☹ OOPS!!! What is wrong with you? Put something valid here as command now!"));
    }
}
