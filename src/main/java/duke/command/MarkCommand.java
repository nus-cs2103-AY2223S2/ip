package duke.command;

import duke.*;
import duke.tasks.Task;

public class MarkCommand extends Command {
    public MarkCommand(String textCmd) {
        super(textCmd);
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        Task markedTask = taskList.markTask(Parser.stringToInt(textCmd.substring(5)));
        ui.printMarkTask(markedTask);
    }
}
