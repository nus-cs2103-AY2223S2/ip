package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.task.Task;
public class DeleteCommand extends Command {
    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task deletedTask = tasks.deleteTask(this.taskIndex);
        return ui.printDeleteTask(deletedTask, tasks.getTaskCount());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
