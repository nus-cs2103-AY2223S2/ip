package reborn.command;

import reborn.data.TaskList;
import reborn.data.exception.RebornException;
import reborn.storage.Storage;
import reborn.ui.Ui;

/**
 * Exits Duke after saving state.
 */
public class ExitCommand extends Command {

    /**
     * Executes the command given by the user.
     *
     * @param tasks   to be modified
     * @param ui      to display changes
     * @param storage to interact with as necessary
     * @return Response string.
     * @throws RebornException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws RebornException {
        storage.save(tasks.tasksToStr());
        return ui.showGoodbye();
    }
    @Override
    public boolean isExit() {
        return true;
    }
}
