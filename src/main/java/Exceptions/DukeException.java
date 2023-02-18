package exceptions;

/**
 * An abstract class that encompasses possible exceptions in Duke
 */
public abstract class DukeException extends Exception {

    /**
     * Creates a Duke Exception
     *
     * @param s Exception Message
     */
    public DukeException(String s) {
        super(s);
    }

}
