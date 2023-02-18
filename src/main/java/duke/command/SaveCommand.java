package duke.command;

import duke.Storage;
import duke.Tasklist;
import duke.Ui;

import java.io.IOException;

/**
 * Class representing the Save Command.
 */
public class SaveCommand extends Command {
    private final String SAVE_INFO;
    private final int SAVE_NO;

    /**
     * Constructor method for SaveCommand.
     *
     * @param saveNo Index of save slot to be saved to.
     * @param saveInfo Message to be included with the save.
     */
    public SaveCommand(int saveNo, String saveInfo) {
        this.SAVE_INFO = saveInfo;
        this.SAVE_NO = saveNo;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String execute(Tasklist tasklist, int saveNo) throws IOException {
        Storage.save(tasklist, SAVE_NO, SAVE_INFO);
        return Ui.showSave(SAVE_NO);
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
