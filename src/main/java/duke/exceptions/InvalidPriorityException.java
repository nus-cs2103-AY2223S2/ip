package duke.exceptions;
public class InvalidPriorityException extends Exception {

    public InvalidPriorityException() {
        super("Priority level doesn't exist bruh");
    }

    public InvalidPriorityException(String message) {
        super(message);
    }
}