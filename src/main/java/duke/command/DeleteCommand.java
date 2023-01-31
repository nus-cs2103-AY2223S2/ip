package duke.command;

import duke.*;
import duke.tasks.Task;

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
