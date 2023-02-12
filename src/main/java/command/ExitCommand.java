package command;

import duke.DukeList;
import duke.Storage;
import duke.Ui;

public class ExitCommand extends Command {
    private DukeList dukeList;
    private DukeList archive;
    private Storage storage;

    public ExitCommand(DukeList dukeList, DukeList archive, Storage storage) {
        this.dukeList = dukeList;
        this.archive = archive;
        this.storage = storage;
    }

    @Override
    public void execute() {
        storage.save(this.dukeList, "list");
        storage.save(this.archive, "archive");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
