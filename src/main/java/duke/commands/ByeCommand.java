package duke.commands;

import duke.Storage;
import duke.Ui;

import duke.exceptions.DukeExceptions;
import duke.tasks.TaskList;

public class ByeCommand implements Command {
    /**
     * Runs when user ends a session. Updates any changes made to Tasks during the session and if there are no errors,
     * the goodbye message is displayed.
     *
     * @param taskList the TaskList used during this session
     * @param ui the Ui needed to display according messages
     * @param storage the Storage used to update the save file
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            storage.updateTasks(taskList);
            ui.showGoodbye();
        } catch (DukeExceptions e) {
            ui.showError(e);
        }
    }
}