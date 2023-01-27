package exceptions;

public class Unimplemented extends DukeException {
    /**
     * Constructor to pass on forward to Exception
     */
    public Unimplemented() {
        super("Apologise the command you just typed has not available yet.");
    }
}
