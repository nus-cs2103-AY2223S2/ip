package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.command.Command;

public class DeleteCommand extends Command {
    private int num;

    public DeleteCommand(int num) {
        this.num = num;
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            ui.deleteTaskResponse(tasks.deleteTask(num - 1) , tasks);
        } catch (IndexOutOfBoundsException e1) {
            ui.taskNotChosenErrorMessage();
        }
    }
}
