package duke.exceptions;

/** An exception for the case that keyword was not specified in a 'find' command. */
public class FindKeywordMissing extends MissingCommandArguments {
    /**
     * Constructor method.
     * 
     * @param errorMessage Error message to display to user
     */
    public FindKeywordMissing() {
        super("Missing search keyword.\nCommand format: 'find <search_keyword>'");
    }
}
