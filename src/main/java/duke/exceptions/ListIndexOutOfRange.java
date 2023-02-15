package duke.exceptions;

/** An exception for the case that keyword was not specified in a 'find' command. */
public class ListIndexOutOfRange extends MissingCommandArguments {
    /**
     * Constructor method.
     *
     * @param errorMessage Error message to display to user
     */
    public ListIndexOutOfRange() {
        super("No task found on the specified index.");
    }
}
