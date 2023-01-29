package alfred.command;

import alfred.task.TaskList;
import alfred.ui.Ui;
import alfred.storage.Storage;
import alfred.exceptions.AlfredException;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AlfredException {
        storage.write(tasks);
        ui.displayBye();
    }

    @Override
    public boolean isExit() {
        return true; // does this part contradict LSP? but command is an abstract class
    }
}
