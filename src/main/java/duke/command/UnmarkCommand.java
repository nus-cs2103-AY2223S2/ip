package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class UnmarkCommand extends Command {
    private int unmarkIdx;

    public UnmarkCommand(int unmarkIdx) {
        this.unmarkIdx = unmarkIdx;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.unmarkIdx >= tasks.size()) {
            throw new DukeException(
                "The task with the given index does not exist.");
        }
        String res = tasks.getTask(this.unmarkIdx).unmark();
        return res;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
