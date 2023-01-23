package sam.storage;

import sam.SamException;

public class SamSaveFailedException extends SamException {
    public SamSaveFailedException() {
        super("Oh no, there was a problem saving your list!");
    }
}