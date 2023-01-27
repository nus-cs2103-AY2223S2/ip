package leo.command;

import leo.storage.Storage;

/**
 * Represents an unmark command input by user.
 */
public class Unmark extends Command {

    public Unmark(Storage s, String c) {
        super(s, c);
        int num = extractTaskNum();
        s.unmark(num);
    }

}
