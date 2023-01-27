package leo.command;

import leo.storage.Storage;

/**
 * Represents an unmark command input by user.
 */
public class UnmarkCommand extends Command {

    public UnmarkCommand(Storage s, String c) {
        super(s, c);
        int num = extractTaskNum();
        s.unmark(num);
    }

}
