package duke.command;

import duke.tasks.Task;
import duke.Parser;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class MarkCommand extends Command {
    public MarkCommand(String textCmd) {
        super(textCmd);
    }

    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        Task markedTask = taskList.markTask(Parser.stringToInt(textCmd.substring(5)));
        return ui.printMarkTask(markedTask);
    }
}
