package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.command.Command;

public class MarkCommand extends Command {

    private int num;

    public MarkCommand(int num) {
        this.num = num;
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            ui.markTaskResponse(tasks.markTask(num, true));
        } catch (IndexOutOfBoundsException e1) {
            ui.taskNotChosenErrorMessage();
        }
    }

}
