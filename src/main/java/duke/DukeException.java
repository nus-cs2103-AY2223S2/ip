package duke;

/**
 * This is a Duke Exception class which returns error messages when the number of parameters do not fit the number needed for each action class.
 * @author yanlings
 */
public class DukeException extends ArrayIndexOutOfBoundsException{
    public DukeException(String message) {
        super(message);
    }
}