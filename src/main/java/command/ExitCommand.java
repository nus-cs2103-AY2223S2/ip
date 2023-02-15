package command;

import duncan.DuncanList;
import duncan.Storage;

public class ExitCommand extends Command {
    private DuncanList duncanList;
    private DuncanList archive;
    private Storage storage;

    public ExitCommand(DuncanList duncanList, DuncanList archive, Storage storage) {
        this.duncanList = duncanList;
        this.archive = archive;
        this.storage = storage;
    }

    @Override
    public void execute() {
        storage.save(this.duncanList, "list");
        storage.save(this.archive, "archive");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
