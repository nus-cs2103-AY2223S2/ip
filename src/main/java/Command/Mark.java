package command;

import storage.Storage;

public class Mark extends ListAction {

    public Mark(Storage s, String command) {
        int num = extractTaskNum(command);
        s.mark(num);
    }

}
