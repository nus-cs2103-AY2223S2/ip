package sam.task;

import sam.SamException;

public class SamMissingTaskTitleException extends SamException {
    public SamMissingTaskTitleException() {
        super("Oops, you forgot a title for your task!");
    }
}