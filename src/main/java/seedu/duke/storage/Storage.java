package seedu.duke.storage;

import seedu.duke.commands.Command;
import seedu.duke.commands.Load;

/**
 * Storage module which keeps a
 */
public class Storage {
    private String filePath = "data/duke.tasks.txt";

    public Command load() {
        Command c = new Load(filePath);
        return c;
    }

    public Storage(String filePath) {
        this.filePath = filePath;
    }
}
