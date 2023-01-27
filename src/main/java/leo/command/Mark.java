package leo.command;

import leo.storage.Storage;

/**
 * Represents a mark command input by user.
 */
public class Mark extends Command {

    public Mark(Storage s, String c) {
        super(s, c);
        int num = extractTaskNum();
        s.mark(num);
    }

}
