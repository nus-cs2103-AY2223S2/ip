package Duke.command;

import Duke.Storage.Storage;
import Duke.TaskList;
import Duke.Ui;

public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        String output;
        if (ui.printAllTasks(tasks) == null) {
            output = String.format(
                    "\n\tThere is no task in the list." + "\n");
            return output;
        }
        return ui.printAllTasks(tasks);
    }
}
