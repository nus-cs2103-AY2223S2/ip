package exceptions;

/**
 * Thrown when user enters a quit command
 */
public class Quit extends DukeException {

    /**
     * This method's constructor passes forward the message to Exception.
     */
    public Quit() {
        super("");
    }

}
