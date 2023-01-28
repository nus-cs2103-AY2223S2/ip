package duke.command;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    private int taskNum;

    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task taskDeleted = tasks.getTask(taskNum - 1);
        tasks.delete(taskNum - 1);
        ui.showDeleteMessage(taskDeleted, String.valueOf(tasks.getLength()));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
