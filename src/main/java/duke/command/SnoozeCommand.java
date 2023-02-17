package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class SnoozeCommand extends Command{
    private final int taskIndex;

    public SnoozeCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.snoozeTask(this.taskIndex);
        return ui.printSnoozeTask(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
