package sam.parser;

import sam.SamException;
/**
 * A SamException thrown when an error occurs during parsing an integer.
 */
public class SamInvalidIntException extends SamException {
    public SamInvalidIntException() {
        super("Oops, I was expecting an integer!");
    }
}
