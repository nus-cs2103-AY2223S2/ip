package duke;

/**
 * Represents Exceptions from Duke.
 */
public class DukeException extends Exception {

    /**
     * Constructor for DukeException.
     *
     * @param errorMessage The Error Message.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
