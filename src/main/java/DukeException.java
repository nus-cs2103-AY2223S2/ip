/**
 * Class DukeException is a subclass of Exception,
 * encapsulates a string describes a checked exception
 * that a Duke object encounters.
 *
 * @author hhchinh2002
 */
public class DukeException extends Exception {
    /**
     * Creates a DukeException object with given exception description.
     *
     * @param description The description for the exception from Duke.
     */
    public DukeException(String description) {
        super(description);
    }


}
