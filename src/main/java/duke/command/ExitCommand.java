package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

public class ExitCommand extends Command {

    public ExitCommand() {
        super(true);
    }

    @Override
    public String execute(TaskList task, Ui ui, Storage storage) {
        return ui.showGoodbye();
    }

}
