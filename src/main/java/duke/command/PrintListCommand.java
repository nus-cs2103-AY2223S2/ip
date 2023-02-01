package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class PrintListCommand extends Command {
    public PrintListCommand() {}

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        System.out.println(tasks.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
