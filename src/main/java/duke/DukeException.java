package duke;

public class DukeException extends Exception {

    /**
     * Holds the error message of the exception
     *
     * @param errorMessage Error message of the exception
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
