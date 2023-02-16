package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.Storage;
import duke.TaskList;
import duke.Task;

public class DeleteCommand extends Command {
    String command;

    public DeleteCommand(String command) {
        this.command = command;
    }


    public void execute(TaskList taskList, Storage storage,  Ui ui) throws DukeException {
        int index = Integer.parseInt(command.replaceAll("delete ", "")) - 1;
        Task tempTask = taskList.getTask(index);
        taskList.deleteTask(index);
        ui.printDeleteTask(tempTask);
    }
}