package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

public class ExitCommand extends Command {
    CommandType command;

    public ExitCommand() {
        this.command = CommandType.bye;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String msg = "Bye. Hope to see you again soon!";
        ui.show(msg);
        super.isExit = true;
    }
}
