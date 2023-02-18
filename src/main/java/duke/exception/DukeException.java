package duke.exception;


/**
 * DukeException meant to be inherited from by all exceptions relating to the DukeBot.
 */
public class DukeException extends Exception {

    public static final String FRAME = "";

    public DukeException(String err) {
        super(err);
    }

}
