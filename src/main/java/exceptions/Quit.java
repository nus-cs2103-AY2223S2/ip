package exceptions;

/**
 * Thrown when user enters a quit command
 */
public class Quit extends DukeException {

    /**
     * Constructor to pass on forward to Exception
     */
    public Quit() {
        super("");
    }

}
