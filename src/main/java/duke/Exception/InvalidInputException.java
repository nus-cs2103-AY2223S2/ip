package duke.Exception;

/**
 * An InvalidInputException a type of DukeException that is thrown when a certain invalid command is input
 * by the user.
 */
public class InvalidInputException extends DukeException {

    /**
     * The constructor for an InvalidInputException.
     * @param input The (invalid) user input.
     */
    public InvalidInputException(String input) {
        super("Sorry, the input " + input + " is not a supported command!");
    }

    @Override
    public String getExceptionType() {
        return "Invalid Input(s)";
    }
}
