/**
 * Custom Exceptions specific to the 'Duke' class.
 *
 * @author Bo Kuan (LG17)
 * @version CS2103T AY22/23 Semester 2
 */

public class DukeException extends Exception {

    /**
     * In Duke Exceptions, we are not interested in the specific name of the
     * exception, just the message.
     */
    protected String message;

    /**
     * Constructor for DukeException Exception.
     *
     * @param errorMessage String describing the error message.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
        this.message = errorMessage;
    }

}
