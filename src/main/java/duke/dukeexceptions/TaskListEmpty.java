package duke.dukeexceptions;

/**
 *  An exception that throws when TaskList is empty.
 */
public class TaskListEmpty extends DukeException {

    /**
     * Constructor for the Exception.
     *
     * @param s string to be thrown during exception.
     */
    public TaskListEmpty(String s) {
        super(s);
    }

    /**
     * {@inheritDoc}
     *
     * @return a warning message when there is missing information.
     */
    @Override
    public String getMessage() {
        return "☹ hmm strange the list is missing and so are my cookies?!?";
    }
}
