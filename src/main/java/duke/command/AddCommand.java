package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Command to add tasks.
 */
public class AddCommand extends Command {
    private String command;

    public AddCommand(String command) {
        this.command = command;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = Task.makeTask(this.command);
        String str = taskList.addTask(task);
        storage.save(taskList);
        return str;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
