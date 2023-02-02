package duke.command;

import duke.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

public class ExitCommand extends Command {

    @Override
    public void initCommand(TaskList tasks, Ui ui, Storage storage) {
        storage.saveData(tasks);
        ui.displayGoodbyeMessage();
        terminate();
    }
    
}