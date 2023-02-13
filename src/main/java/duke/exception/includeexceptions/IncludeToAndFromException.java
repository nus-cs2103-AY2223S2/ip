package duke.exception.includeexceptions;

/**
 * Thrown when
 */
public class IncludeToAndFromException extends IncludeException {
    public IncludeToAndFromException() {
        super("\n" + "     Error! Did you forget to include the \"/from\" and \"/to\" in your command?" + "\n");
    }
}
