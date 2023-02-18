package duke.command;

import duke.Storage;
import duke.Tasklist;
import duke.Ui;

/**
 * Class representing the delete command.
 */
public class DeleteCommand extends Command {
    private final int INDEX;

    /**
     * Constructor method for AddCommand.
     *
     * @param index Index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.INDEX = index;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String execute(Tasklist tasklist, int saveNo) throws Exception {
        String returnedMsg = Ui.showDelete(tasklist.get(INDEX - 1)) + "\n" + Ui.showTasklistSize(tasklist);
        tasklist.delete(INDEX);
        Storage.save(tasklist, saveNo);
        return returnedMsg;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
