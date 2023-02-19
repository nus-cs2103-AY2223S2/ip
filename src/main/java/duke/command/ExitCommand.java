package duke.command;

import duke.Command;
import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.showExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}