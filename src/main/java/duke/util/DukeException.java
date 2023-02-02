package duke.util;

/*
 * Represents an exception that is thrown by Duke.
 * The messages are passed to the DukeException object.
 * The DukeException object is then thrown.
 * The message is then printed by the Ui object.
 */
public class DukeException extends Exception{

    /*
     * Constructor for a new DukeException object.
     * 
     * @param message Message to be printed by the Ui object.A
     */
    public DukeException(String message) {
        super(message);
    }
}
