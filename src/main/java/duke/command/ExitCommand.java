package duke.command;

import duke.main.Storage;
import duke.main.Tasklist;
import duke.main.Ui;

public class ExitCommand extends Command {

    public String execute(Tasklist taskList, Ui ui, Storage storage) {
        super.switchExitCondition();
        return ui.printBye();
    }
}
