package duke.duke_exception;

/**
 * An Exception class used to handle invalid commands where Duke is unable to respond to.
 * 
 * 
 * @author Brian Quek
 */
public class DukeException extends Exception {
    private String message;

    /**
     * Constructor for the DukeException object.
     */
    public DukeException(int index) {
        switch (index) {
            /**
             * Handles cases where the command is not present.
             */
            case 0:
                message = "Empty command, please key in a valid command.";
                break;
            /**
             * Handles cases where the command is present but not valid.
             */
            case 1:
                message = "Invalid command structure, please key in a valid command.";
                break;
            /**
             * Handles cases where the command is present but parameters are not present.
             */
            case 2:
                message = "Input parameters cannot be empty!";
                break;
            /**
             * Handles cases where the command and parameters are present but invalid parameter
             * typing.
             */
            case 3:
                message = "Input parameters must be numerical!";
                break;
            /**
             * Handles cases for deadline/event commands where the /by, /from or /to are missing.
             */
            case 4:
                message = "Missing /by, /from or /by parameters.";
                break;
            default:
                message = "Unknown Error";
        }
    }

    public DukeException(String msg) {
        this.message = msg;
    }

    /**
     * @return a string explaining what kind of invalid input has been written.
     */
    @Override
    public String toString() {
        return "System Error: " + this.message;
    }
}
