package duke.exceptions;
public class InvalidPriorityException extends Exception {

    public InvalidPriorityException() {
        super("Apologies but this priority level does not exist");
    }

    public InvalidPriorityException(String message) {
        super(message);
    }
}