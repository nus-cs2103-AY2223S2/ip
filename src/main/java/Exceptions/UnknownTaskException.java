package exceptions;

/**
 * This class is an exception for Duke receiving a command that is not within
 * Duke's capabilities
 */
public class UnknownTaskException extends DukeException {

    /**
     * Creates an exception of Duke not understanding a specified command
     *
     * @param s specified user command
     */
    public UnknownTaskException(String s) {
        super("OH NO!! I DONT KNOW WHAT " + s.toUpperCase() + " MEANS!!");
    }
}
