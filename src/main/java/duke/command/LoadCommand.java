package duke.command;

import duke.SaveList;
import duke.Storage;
import duke.Tasklist;
import duke.Ui;

import java.io.IOException;

/**
 * Class representing the load command.
 */
public class LoadCommand extends Command{
    private final int SAVE_NO;

    /**
     * Constructor method for LoadCommand.
     *
     * @param saveNo Index of save to be loaded.
     */
    public LoadCommand(int saveNo) {
        this.SAVE_NO = saveNo;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String execute(Tasklist tasklist, int saveNo) throws IOException {
        SaveList saveData = Storage.readSaveData();
        Storage.writeSaveData(saveData, SAVE_NO);
        return Ui.showLoad(this.SAVE_NO);
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
