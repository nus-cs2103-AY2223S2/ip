package leo.command;

import leo.storage.Storage;

public class UnmarkCommand extends Command {

    public UnmarkCommand(Storage s, String c) {
        super(s, c);
        int num = extractTaskNum();
        s.unmark(num);
    }

}
