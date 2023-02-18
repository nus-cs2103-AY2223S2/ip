package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.Storage;
import duke.TaskList;
import duke.Task;

public class DeleteCommand extends Command {
    String command;

    /**
     * Class constructor.
     *
     * @param command the task to be deleted.
     */
    public DeleteCommand(String command) {
        this.command = command;
    }

    /**
     * Returns a "taskDeleted" message.
     *
     * @param taskList the list of tasks.
     * @param storage the items read from the file.
     * @param ui methods to be used to interact with the user.
     * @return "taskDeleted" message.
     * @throws DukeException
     */
    public String execute(TaskList taskList, Storage storage,  Ui ui) throws DukeException {
        int index = Integer.parseInt(command.replaceAll("delete ", "")) - 1;
        Task tempTask = taskList.getTask(index);
        taskList.deleteTask(index);
        return ui.printDeleteTask(tempTask);
    }
}