package sam.parser;

import sam.SamException;

public class SamInvalidTaskException extends SamException {
    public SamInvalidTaskException() {
        super("Oops, that task does not exist!");
    }
}