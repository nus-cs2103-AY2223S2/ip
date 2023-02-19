package treebot.exception;

/**
 * Signals an exception faced by the <code>TreeBot</code>.
 *
 * This is the general class of exceptions that occur within <code>TreeBot</code>.
 */
public class TreeBotException extends Exception {

    /**
     * Constructs a <code>TreeBot</code> exception with the given error message.
     * @param errorMessage
     */
    public TreeBotException(String errorMessage) {
        super(errorMessage);
    }

}
