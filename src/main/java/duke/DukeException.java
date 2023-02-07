package duke;

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
