package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.task.Task;
public class TickCommand extends Command{
    private final int taskIndex;

    public TickCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.tickTask(this.taskIndex);
        return ui.printTickTask(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
