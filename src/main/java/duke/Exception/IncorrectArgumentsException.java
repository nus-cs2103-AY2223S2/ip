package duke.Exception;

/**
 * An IncorrectArgumentsException a type of DukeException that is thrown when a certain valid command expects a
 * certain number of arguments but the number of arguments the user input is not accurate.
 */
public class IncorrectArgumentsException extends DukeException {

    /**
     * The constructor for an incorrectArgumentsException.
     * @param command The name of the valid command.
     * @param numberExpected The number of arguments expected for the valid command.
     * @param numberActual The actual number of arguments that the user input.
     */
    public IncorrectArgumentsException(String command, int numberExpected, int numberActual) {
        super(String.format("Sorry, command: %s expected %s arguments, detected %s arguments!",
                command, numberExpected, numberActual));
    }

    @Override
    public String getExceptionType() {
        return "Incorrect Argument(s)";
    }
}
