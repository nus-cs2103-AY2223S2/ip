package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

public class DeleteCommand extends Command {

    private static Integer START_INDEX_OF_DESCRIPTION = 7;

    public DeleteCommand(String textCmd) {
        super(textCmd);
    }

    public String execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        Task removedTask = taskList.deleteTask(
                Parser.stringToInt(textCmd.substring(START_INDEX_OF_DESCRIPTION)));
        return ui.printDeleteTask(removedTask, taskList.getNumTasks());
    }
}
