package Duke.command;

import Duke.Storage.Storage;
import Duke.TaskList;
import Duke.Ui;

public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        return ui.printAllTasks(tasks);
    }
}
