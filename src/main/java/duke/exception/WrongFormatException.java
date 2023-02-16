package duke.exception;

/**
 * WrongFormatException is thrown when user does not enter inputs in the correct format with the commands
 */
public class WrongFormatException extends Exception {

    /**
     * Constructor for WrongFormatException
     * @param message Shows the correct format of inputs
     */
    public WrongFormatException(String message) {
        super("HEY!! Pleaseeee enter in a correct format: " + " '" + message + "'");
    }
}
