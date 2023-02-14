package duke.exceptions;

/** An exception for the case that keyword was not specified in a 'find' command. */
public class ListIndexMissing extends MissingCommandArguments {
    /**
     * Constructor method.
     * 
     * @param errorMessage Error message to display to user
     */
    public ListIndexMissing() {
        super("Missing list index to execute on.\nCommand format: '<command> <list_index>'");
    }
}
