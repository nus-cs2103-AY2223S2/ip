package duke.dukeexception;

/**
 * Class to manage problems during execution of Duke CLI
 */
public class DukeException extends Exception {

    public DukeException(String message) {
        super(message);
    }

    /**
     * @return String version of exception
     */
    @Override
    public String toString() {
        return super.getMessage() + "\n";
    }
}
