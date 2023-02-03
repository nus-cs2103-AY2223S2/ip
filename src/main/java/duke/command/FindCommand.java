package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

public class FindCommand extends Command {
    private String InputToSearch;
    public FindCommand(String input) {
        this.InputToSearch = input;
    }
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> foundTasks = taskList.findTasks(this.InputToSearch);
        if (foundTasks.size() == 0) {
            ui.showMessage("We could not find any matching tasks.");
        } else {
            ui.showMessage();
            for (Task foundTask : foundTasks) {
                if (foundTask instanceof Todo) {
                    ui.showMessage(foundTasks.indexOf(foundTask) + 1 + ". [" + foundTask.getSymbol() + "] "
                            + "[" + foundTask.getStatusIcon() + "] " + foundTask.getDescription());
                } else {
                    ui.showMessage(foundTasks.indexOf(foundTask) + 1 + ". [" + foundTask.getSymbol() + "] "
                                  + "[" + foundTask.getStatusIcon() + "] " + foundTask.getDescription()
                                  + " (" + foundTask.getDuedateString() + ")");
                }
            }
        }
    }

    public boolean isExit() {
        return false;
    }
}
