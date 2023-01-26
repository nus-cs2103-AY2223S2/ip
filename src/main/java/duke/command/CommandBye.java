package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.task.Ui;

public class CommandBye extends Command{
    public CommandBye(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.bye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
