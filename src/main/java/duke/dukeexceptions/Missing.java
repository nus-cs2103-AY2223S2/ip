package duke.dukeexceptions;

/**
 *  An exception that throws when there is a missing date and time.
 */
public class Missing extends DukeException {

    /**
     * Constructor for the Exception.
     *
     * @param s string to be thrown during exception.
     */
    public Missing(String s) {
        super(s);
    }

    /**
     * {@inheritDoc}
     *
     * @return a warning message when there is missing information.
     */
    @Override
    public String getMessage() {
        return "☹ NOM NOM NOM!! Where is your date/time??";
    }
}
