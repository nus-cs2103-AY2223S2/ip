package storage;

import commands.Command;
import commands.Load;
import tasklist.TaskList;

/**
 * Storage module which keeps a
 */
public class Storage {
    private String filePath = "data/tasks.txt";

    public Command load() {
        Command c = new Load(filePath);
        return c;
    }

    public Storage(String filePath) {
        this.filePath = filePath;
    }
}
