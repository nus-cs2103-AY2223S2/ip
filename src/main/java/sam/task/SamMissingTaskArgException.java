package sam.task;

import sam.SamException;

public class SamMissingTaskArgException extends SamException {
    public SamMissingTaskArgException() {
        super("Oops, you're missing an argument!");
    }
}