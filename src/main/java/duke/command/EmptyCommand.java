package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class EmptyCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.reply("Please input a command");
    }

}
