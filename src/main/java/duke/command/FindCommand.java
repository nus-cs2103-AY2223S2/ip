package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    String regex;

    public FindCommand(String regex) {
        this.regex = regex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printFind(taskList, this.regex);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
