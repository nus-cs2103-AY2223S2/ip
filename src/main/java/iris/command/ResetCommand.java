package iris.command;

import iris.TaskList;
import iris.TaskStore;
import iris.exception.IrisException;
import iris.Ui;

public class ResetCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, TaskStore taskStore) throws IrisException {
        tasks.clear();
        taskStore.reset();
        Ui.output("Your task list has been resetted. You have no commands.");
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof ResetCommand;
    }
}
