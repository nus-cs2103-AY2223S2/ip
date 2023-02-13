package duke.exceptions;

public class InvalidArgumentException extends DukeException {
    public InvalidArgumentException(String arg, String usage) {
        super("The argument " + arg + " you specified should be " + usage + " Get it right!");
    }
}
