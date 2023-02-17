package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Task;
import duke.UI.UI;

public class DeleteCommand extends Command {
    public static final String COMMAND = "delete";
    private String[] index;

    public DeleteCommand(String[] index) {
        this.index = index;
    }

    @Override
    public void runCommand(TaskList taskList, UI ui, Storage storage) {
        Task deletedTask = taskList.deleteTask(Character.getNumericValue(index[1].charAt(0)), storage);
        ui.showDelete(deletedTask, taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
