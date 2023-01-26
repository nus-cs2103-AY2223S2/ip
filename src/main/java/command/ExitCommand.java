package command;

import duke.DukeList;
import duke.Storage;

/**
 * Represents a Command that saves the current list and signals ends the programme
 */
public class ExitCommand extends Command {
    private DukeList dukeList;
    private Storage storage;

    /**
     * Creates an ExitCommand with the given DukeList and Storage
     * @param dukeList the DukeList that is to be saved
     * @param storage the Storage to which the DukeList will be saved to
     */
    public ExitCommand(DukeList dukeList, Storage storage) {
        this.dukeList = dukeList;
        this.storage = storage;
    }

    /**
     * Saves the current DukeList to Storage
     */
    @Override
    public void execute() {
        storage.saveList(this.dukeList);
    }


    @Override
    public boolean isExit() {
        return true;
    }
}
