package duke.command;

import duke.SaveList;
import duke.Tasklist;
import duke.Ui;

/**
 * Class representing the show saves command.
 */
public class ShowSavesCommand extends Command{
    private final SaveList SAVE_LIST;

    /**
     * Constructor method for ShowListCommand.
     *
     * @param saveList The current list of saves.
     */
    public ShowSavesCommand(SaveList saveList) {
        SAVE_LIST = saveList;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String execute(Tasklist tasklist, int saveNo) {
        return Ui.listSaves(SAVE_LIST);
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
