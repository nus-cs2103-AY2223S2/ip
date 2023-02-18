package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.Storage;

public class ByeCommand extends Command {
    /**
     * Class constructor.
     */
    public ByeCommand() {}

    /**
     * Returns the goodbye message.
     *
     * @param taskList the list of tasks.
     * @param storage the items read from the file.
     * @param ui methods to be used to interact with the user.
     * @return goodbye message.
     * @throws DukeException
     */
    public String execute(TaskList taskList, Storage storage,  Ui ui) throws DukeException {
        storage.writeToFile(taskList);
        return ui.showGoodbye();
    }

    @Override
    public boolean checkEnd() {
        return true;
    }
}
