package duke.exception;

/**
 * Exception thrown when command is not recognized
 */
public class WrongFormatException extends Exception {
    public WrongFormatException() {
        super("OOPS! Please follow the format! Do not enter anything after 'list' and 'bye'!");
    }

}
