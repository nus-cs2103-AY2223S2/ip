package duke.command;


import duke.storage.TaskList;
import duke.ui.Ui;
public class InvalidCommand extends Command {

    @Override
    public String execute(TaskList list, Ui ui) {
        return ui.printInvalidCommandMessage();
    }
}
