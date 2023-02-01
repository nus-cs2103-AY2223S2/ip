package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class MarkCommand extends Command {
    private int index;

    MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            ui.reply(taskList.mark(index));
            storage.saveState(taskList);
        } catch (DukeException e) {
            ui.reply(e.toString());
        }
    }
}
