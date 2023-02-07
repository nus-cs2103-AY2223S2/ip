package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {
    private int deleteIdx;
    
    public DeleteCommand(int deleteIdx) {
        this.deleteIdx = deleteIdx;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String res = tasks.delete(deleteIdx);
        return res;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
