package Command;

import Storage.Storage;

public class Delete extends ListAction {

    public Delete(Storage s, String c) {
        int num = extractTaskNum(c);
        s.delete(num);
    }

}
