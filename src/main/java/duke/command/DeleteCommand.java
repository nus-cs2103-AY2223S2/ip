package duke.command;

import duke.tasks.Task;
import duke.Parser;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {

    public DeleteCommand(String textCmd) {
        super(textCmd);
    }

    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        Task removedTask = taskList.deleteTask(
                Parser.stringToInt(textCmd.substring(7)));
        ui.printDeleteTask(removedTask, taskList.getNumTasks());
    }
}
