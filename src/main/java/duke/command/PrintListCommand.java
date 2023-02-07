package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class PrintListCommand extends Command {
    public PrintListCommand() {}

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String res = tasks.toString();
        return res;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
