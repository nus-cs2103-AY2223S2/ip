package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class UnmarkCommand extends Command {
    private int index;

    UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            ui.reply(taskList.unmark(index));
            storage.saveState(taskList);
        } catch (DukeException e) {
            ui.reply(e.toString());
        }
    }
}
