package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.*;

public class EventCommand extends Command {
    private String input;

    public EventCommand(String input) {
        this.input = input;
    }

    public void execute(TaskList taskList, Ui inter, Storage store) {
        Task added = taskList.event(input);
        inter.taskAdded(added, taskList.size());
        store.writeTasks(taskList);
    }

    public boolean isExit() {
        return false;
    }
}
