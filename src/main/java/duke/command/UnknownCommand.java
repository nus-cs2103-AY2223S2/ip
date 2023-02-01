package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class UnknownCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.reply("Unknown command, please try again");
    }
}
