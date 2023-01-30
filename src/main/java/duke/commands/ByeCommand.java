package duke.commands;

import duke.Ui;
import duke.Storage;
import duke.exceptions.DukeExceptions;
import duke.tasks.TaskList;

public class ByeCommand implements Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            storage.updateTasks(taskList); // handle error
            ui.showGoodbye();
        } catch(DukeExceptions e) {
            ui.showError(e);
        }
    }
}