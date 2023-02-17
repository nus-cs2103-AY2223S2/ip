package duke;

/**
 * Class duke.DukeException is a subclass of Exception,
 * encapsulates a string describes a checked exception
 * that a duke.Duke object encounters.
 *
 * @author hhchinh2002
 */
public class DukeException extends Exception {
    /**
     * Creates a duke.DukeException object with given exception description.
     *
     * @param description The description for the exception from duke.Duke.
     */
    public DukeException(String description) {
        super(description);
    }


}
