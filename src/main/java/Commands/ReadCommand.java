package Commands;

import Exceptions.NoTaskException;
import Storage.Storage;
import Storage.TaskList;
import Ui.Ui;

public class ReadCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            throw new NoTaskException(null);
        }
        ui.showList();
        tasks.showTasks();
    }

    @Override
    public boolean isContinueConvo() {
        return true;
    }
}
