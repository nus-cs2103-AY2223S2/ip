package sam.parser;

import sam.SamException;

/**
 * A SamException thrown when the user gives an unknown command.
 */
public class SamUnknownCommandException extends SamException {
    public SamUnknownCommandException() {
        super("Sorry, I don't know what that means");
    }
}
