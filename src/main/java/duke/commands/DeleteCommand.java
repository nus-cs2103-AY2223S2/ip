package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.*;

public class DeleteCommand extends Command {
    private String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    public void execute(TaskList taskList, Ui inter, Storage store) {
        Task changed = taskList.delete(input);
        inter.delete(changed, taskList.size());
        store.writeTasks(taskList);
    }

    public boolean isExit() {
        return false;
    }
}
