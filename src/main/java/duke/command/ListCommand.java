package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {

    public ListCommand(String fullCommand) {

        super(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.list();
    }

    @Override
    public boolean isExit() {

        return false;
    }
}
