package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

public class MarkCommand extends Command {

    private static Integer START_INDEX_OF_DESCRIPTION = 5;

    public MarkCommand(String textCmd) {
        super(textCmd);
    }

    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        Task markedTask = taskList.markTask(Parser.stringToInt(textCmd.substring(START_INDEX_OF_DESCRIPTION)));
        return ui.printMarkTask(markedTask);
    }
}
