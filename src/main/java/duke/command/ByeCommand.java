package duke.command;

import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

public class ByeCommand extends Command {
    
    public ByeCommand(String[] command) {
        super(command);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.goodBye();
    }

    public boolean isExit() {
        return true;
    }
}