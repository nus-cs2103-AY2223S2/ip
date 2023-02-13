package duke.Exception;

/**
 * An InvalidArgumentsException a type of DukeException that is thrown when a certain valid command expects
 * arguments in a certain format, and detects the correct number of arguments but in an invalid format.
 */
public class InvalidArgumentsException extends DukeException {

    /**
     * The constructor for an InvalidArgumentsException.
     */
    public InvalidArgumentsException() {
        super("Sorry, the arguments that you have input are in an incorrect format! Please try again!");
    }

    @Override
    public String getExceptionType() {
        return "Invalid Arguments";
    }
}
