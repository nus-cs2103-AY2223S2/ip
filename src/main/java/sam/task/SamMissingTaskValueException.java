package sam.task;

import sam.SamException;

public class SamMissingTaskValueException extends SamException {
    public SamMissingTaskValueException() {
        super("Oops, an argument is missing a value!");
    }
}