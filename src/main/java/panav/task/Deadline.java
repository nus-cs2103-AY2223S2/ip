package panav.task;

/**
 * Class to represent a Deadline task. It has attributes for its deadline time.
 */
public class Deadline extends Task {

    public static final String COMMAND_WORD = "deadline";
    protected String by;

    /**
     * Constructor to initialise attributes.
     * @param description description of deadline.
     * @param by deadline timeframe.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns command word of task.
     * @return command word.
     */
    @Override
    public String getCommand() {
        return Deadline.COMMAND_WORD;
    }

    /**
     * Method to get that just returns the String representation of the tasks
     * containing just that part on which the 'find' command can search.
     * For eg. a find command should not be able to search "find from" and then
     * all the events are displayed. It should only be able to search the description and
     * timeframes.
     * @return searchable part of the string
     */
    @Override
    public String findablePart() {
        String findablePart = this.description + this.by;
        return findablePart;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
