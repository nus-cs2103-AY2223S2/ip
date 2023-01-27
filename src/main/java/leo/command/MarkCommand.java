package leo.command;

import leo.storage.Storage;

/**
 * Represents a mark command input by user.
 */
public class MarkCommand extends Command {

    public MarkCommand(Storage s, String c) {
        super(s, c);
        int num = extractTaskNum();
        s.mark(num);
    }

}
