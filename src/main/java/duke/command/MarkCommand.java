package duke.command;

import duke.Storage;
import duke.Tasklist;
import duke.Ui;

public class MarkCommand extends Command {
    private int index;
    private boolean isMark;

    public MarkCommand(int index, boolean isMark) {
        this.index = index;
        this.isMark = isMark;
    }
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
    @Override
    public boolean isExit() {
        return false;
    }
}
