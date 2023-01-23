package sam.command;

import sam.Ui;
import sam.storage.Storage;
import sam.task.TaskList;

public class ExitCommand extends Command {
    public ExitCommand(String args) {
        super(args);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.talk("Goodbye!");
    }
}
