package sam.storage;

import sam.SamException;

public class SamLoadFailedException extends SamException {
    public SamLoadFailedException() {
        super("Oh no, there was a problem loading your list!");
    }
}