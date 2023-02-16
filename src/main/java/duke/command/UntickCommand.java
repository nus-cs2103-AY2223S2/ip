package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.task.Task;

public class UntickCommand extends Command{
    private final int taskIndex;

    public UntickCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.untickTask(this.taskIndex);
        return ui.printUntickTask(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
