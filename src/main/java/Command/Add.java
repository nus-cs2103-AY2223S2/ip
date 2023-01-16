package Command;

import Storage.Storage;

public class Add {

    public Add(Storage s, String task) {
        s.addTask(task);
    }
}
