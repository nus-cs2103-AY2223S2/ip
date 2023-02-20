package duke;

/**
 * DukeException class thrown by Duke application
 */
public class DukeException extends Exception{

    /**
     * Constructor for instantiating a DukeException object
     * @param message error message to be displayed
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Returns error message
     * @return String error message
     */
    public String getMessage() {
        return super.getMessage();
    }

}
