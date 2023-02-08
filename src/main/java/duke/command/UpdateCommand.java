package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class UpdateCommand extends Command {
    private int index;
    private String command;

    public UpdateCommand(int index, String command) {
        this.index = index;
        this.command = command;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String str = taskList.updateTask(this.index, this.command);
        storage.save(taskList);
        return str;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
