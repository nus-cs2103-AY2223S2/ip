package sam.parser;

import sam.SamException;

/**
 * A SamException thrown when a non-existing task is retrieved.
 */
public class SamInvalidTaskException extends SamException {
    public SamInvalidTaskException() {
        super("Oops, that task does not exist!");
    }
}
