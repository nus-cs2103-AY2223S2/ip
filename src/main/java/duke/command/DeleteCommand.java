package duke.command;

import duke.Storage;
import duke.Tasklist;
import duke.Ui;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(Tasklist tasklist) throws Exception {
        String returnedMsg = Ui.showDelete(tasklist.get(index - 1)) + "\n" + Ui.showTasklistSize(tasklist);
        tasklist.delete(index);
        Storage.save(tasklist);
        return returnedMsg;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
