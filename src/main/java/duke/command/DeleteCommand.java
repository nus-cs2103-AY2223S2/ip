package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {

    private int index;

    DeleteCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            ui.reply(taskList.deleteTask(index));
            storage.saveState(taskList);
        } catch (DukeException e) {
            ui.reply(e.toString());
        }
    }
}
