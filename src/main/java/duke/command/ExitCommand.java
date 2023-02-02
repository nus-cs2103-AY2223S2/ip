package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
public class ExitCommand extends Command{
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks.getTasks());
        return ui.printGoodbyeMsg();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
