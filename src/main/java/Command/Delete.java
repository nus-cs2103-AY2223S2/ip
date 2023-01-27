package command;

import storage.Storage;

public class Delete extends Command {

    public Delete(Storage s, String c) {
        super(s, c);
        int num = extractTaskNum();
        s.delete(num);
    }

}
