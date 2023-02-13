package duke.exception.includeexceptions;

/**
 * Thrown when a AddDeadline command does not include the "/by" keyword.
 */
public class IncludeByException extends IncludeException {
    public IncludeByException() {
        super("\n" + "     Error! Did you forget to include the \"/by\" in your command?" + "\n");
    }
}
