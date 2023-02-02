package duke.command;

import duke.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

public class ListCommand extends Command{

    @Override
    public void initCommand(TaskList tasks, Ui ui, Storage storage) {
        ui.displayTaskList(tasks);
        
    }
    
}
