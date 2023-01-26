package command;

import duke.DukeList;
import duke.Storage;

public class ExitCommand extends Command {
    private DukeList dukeList;
    private Storage storage;

    public ExitCommand(DukeList dukeList, Storage storage) {
        this.dukeList = dukeList;
        this.storage = storage;
    }

    @Override
    public void execute() {
        storage.saveList(this.dukeList);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
