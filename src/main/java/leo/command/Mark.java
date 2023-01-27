package leo.command;

import leo.storage.Storage;

public class Mark extends Command {

    public Mark(Storage s, String c) {
        super(s, c);
        int num = extractTaskNum();
        s.mark(num);
    }

}
