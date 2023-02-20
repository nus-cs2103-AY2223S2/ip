package duke;

/**
 * Class for DukeException, which is thrown when there is a Duke specific error.
 *
 * @author Eric Leow Yu Quan
 */
public class DukeException extends Exception{

    /**
     * Construtor for DukeException instance.
     *
     * @param errorMessage the error message that should be shown to the user.
     */
    public DukeException(String errorMessage) {
        super(":( Sorry! " + errorMessage +". Please try again.");
    }
}
