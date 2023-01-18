package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.*;

public class MarkCommand extends Command {
    private String input;

    public MarkCommand(String input) {
        this.input = input;
    }

    public void execute(TaskList taskList, Ui inter, Storage store) {
        Task changed = taskList.mark(input);
        inter.mark(changed);
        store.writeTasks(taskList);
    }

    public boolean isExit() {
        return false;
    }
}
