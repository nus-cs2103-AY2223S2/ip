package command;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class ExitCommand extends Command {
    
    public ExitCommand(String input) {
        super(input);
        super.toggleIsExit();
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
    }
}
