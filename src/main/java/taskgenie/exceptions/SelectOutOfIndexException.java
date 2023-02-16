package exceptions;

/**
 * This class is used to throw an exception for selecting an item that is not in the list.
 */
public class SelectOutOfIndexException extends TaskGenieException {
    /**
     * Constructor for the SelectOutOfIndexException.
     * @param err The error.
     */
    public SelectOutOfIndexException(Throwable err) {
        super("Sorry! You selected a number that does not exists on the list.", err);
    }
}
