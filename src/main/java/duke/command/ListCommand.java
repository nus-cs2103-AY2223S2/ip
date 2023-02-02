package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand implements Command {
    @Override
    public String getCommandName() {
        return "list";
    }

    @Override
    public String getCommandRegexPattern() {
        return "^list$";
    }

    @Override
    public String getCommandPattern() {
        return "list";
    }

    /**
     * Lists tasks in task list.
     *
     * @param ui       User interface.
     * @param taskList Task list.
     * @param storage  Storage.
     * @param args     Argument list; Does not take in any arguments.
     */
    @Override
    public void run(Ui ui, TaskList taskList, Storage storage, String... args) {
        ui.showLine();
        ui.showText("Here are the tasks in your list:");
        for (int taskNo = 1; taskNo <= taskList.getTotalTasks(); taskNo++) {
            ui.showText(String.format("%d. %s", taskNo, taskList.getTask(taskNo).toString()));
        }
        ui.showLine();
    }
}
