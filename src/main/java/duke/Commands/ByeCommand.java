package duke.Commands;

import duke.Storage.Storage;
import duke.TaskList.TaskList;
import duke.Ui.Ui;

public class ByeCommand implements Command {
    
    /**
     * Prints the bye message.
     * 
     * @param tasks The current task list.
     * @param ui The ui object that handles printing to the user.
     * @param storage The storage object that handles saving to the data file.
     * @throws DukeException Exception should not be thrown in this function in this class.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
