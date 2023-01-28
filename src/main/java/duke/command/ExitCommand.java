package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class ExitCommand extends Command {
    private CommandType command;

    public ExitCommand() {
        this.command = CommandType.bye;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String msg = "Bye. Hope to see you again soon!";
        ui.show(msg);
        super.setExit();
    }
}
