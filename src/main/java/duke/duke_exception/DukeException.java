package duke.duke_exception;

/**
 * An Exception class used to handle invalid commands where Duke is unable to respond to.
 * 
 * 
 * @author Brian Quek
 */
public class DukeException extends Exception {
    private String message;

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
