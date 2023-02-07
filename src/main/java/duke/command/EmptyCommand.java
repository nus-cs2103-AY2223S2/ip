package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class EmptyCommand extends Command {
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return "Please input a command";
    }
}
