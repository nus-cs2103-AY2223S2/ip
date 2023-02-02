package duke;

/** Thrown when an invalid command is input */
public class InvalidCommandException extends Exception{
    public InvalidCommandException(String s) {
        super(s);
    }
}
