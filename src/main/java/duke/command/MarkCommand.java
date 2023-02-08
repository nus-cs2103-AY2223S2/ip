package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class MarkCommand extends Command {
    private int markIdx;

    public MarkCommand(int markIdx) {
        this.markIdx = markIdx;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.markIdx >= tasks.size()) {
            throw new DukeException(
                "The task with the given index does not exist.");
        }
        String res = tasks.get(this.markIdx).markDone();
        return res;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
