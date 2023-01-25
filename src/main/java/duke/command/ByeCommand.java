package duke.command;

import duke.storage.StorageList;
import duke.task.TaskList;
import duke.ui.Ui;

public class ByeCommand extends Command {

    public ByeCommand() {

    }

    public boolean execute(TaskList tasks, Ui ui, StorageList storage) {
        System.out.println("See you soon!");
        return true;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}