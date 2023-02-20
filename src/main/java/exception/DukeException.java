package exception;

/**
 * Captures exceptions specific to duke.Duke
 */
public class DukeException extends Exception{
    /**
     * Constructor for DukeException object
     * @param msg Message which describes error
     */
    public DukeException(String msg){
        super(msg);
    }
}
