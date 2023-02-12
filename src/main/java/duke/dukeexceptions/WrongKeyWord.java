package duke.dukeexceptions;

/**
 *  An exception that throws a wrong command is given.
 */
public class WrongKeyWord extends DukeException {

    /**
     * Constructor for the Exception.
     *
     * @param s string to be thrown during exception.
     */
    public WrongKeyWord(String s) {
        super(s);
    }

    /**
     * {@inheritDoc}
     *
     * @return a warning message when there is missing information.
     */
    @Override
    public String getMessage() {
        return "☹ HEY stop talking rubbish!! I could be eating cookies instead";
    }
}
