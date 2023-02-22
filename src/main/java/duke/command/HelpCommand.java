package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.TextUi;

public class HelpCommand extends Command {
    @Override
    public String execute(TaskList tasksList, TextUi ui, Storage storage) {
        return ui.showHelpMessage();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
