package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class UnknownCommand extends Command {
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return "Unknown command, please try again";
    }
}
