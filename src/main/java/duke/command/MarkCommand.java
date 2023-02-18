package duke.command;

import duke.Storage;
import duke.Tasklist;
import duke.Ui;

/**
 * Class representing the mark command.
 */
public class MarkCommand extends Command {
    private int index;
    private boolean isMark;

    /**
     * Constructor method for MarkCommand
     *
     * @param index Index of the task to be marked.
     * @param isMark Mark or unmark.
     */
    public MarkCommand(int index, boolean isMark) {
        this.index = index;
        this.isMark = isMark;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String execute(Tasklist tasklist, int saveNo) throws Exception {
        if (this.isMark) {
            tasklist.mark(index);
        } else {
            tasklist.unmark(index);
        }
        Storage.save(tasklist, saveNo);
        return this.isMark ? Ui.showMark(tasklist.get(index - 1)) : Ui.showUnmark(tasklist.get(index - 1));
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
