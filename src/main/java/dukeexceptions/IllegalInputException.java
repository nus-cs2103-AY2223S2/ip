package dukeexceptions;

/**
 * An exception that is thrown when an Illegal input is keyed in by the user
 * @author clydelhui
 */
public class IllegalInputException extends DukeException {
    public IllegalInputException(String s) {
        super(s);
    }

    @Override
    public String toString() {
        return "Sorry, your input for the command is invalid:" + getMessage();
    }
}
