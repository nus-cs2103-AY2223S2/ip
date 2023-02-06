package command;

import duke.DukeList;
import duke.Storage;
import duke.Ui;

public class ExitCommand extends Command {
    private DukeList dukeList;
    private Storage storage;
    private Ui ui;

    public ExitCommand(DukeList dukeList, Storage storage, Ui ui) {
        this.dukeList = dukeList;
        this.storage = storage;
        this.ui = ui;
    }

    @Override
    public void execute() {
        storage.saveList(this.dukeList, this.ui);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
