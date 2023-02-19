package tigerlily.commands;

import tigerlily.Storage;
import tigerlily.Ui;

import tigerlily.exceptions.DukeExceptions;
import tigerlily.tasks.TaskList;

public class ByeCommand implements Command {
    /**
     * Runs when user ends a session. Updates any changes made to Tasks during the session and if there are no errors,
     * the goodbye message is displayed.
     *
     * @param taskList the TaskList used during this session
     * @param ui the Ui needed to display according messages
     * @param storage the Storage used to update the save file
     * @return the Bye message
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            storage.updateTasks(taskList);
            return ui.showBye();
        } catch (DukeExceptions e) {
            return ui.showError(e);
        }
    }
}