package duke.exceptions;

public class InvalidCmdValueException extends Exception {

    public InvalidCmdValueException() {
        super("Please input a correct value");
    }
}
