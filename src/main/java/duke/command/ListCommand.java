package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.printTasks(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
