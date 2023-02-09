package duke.command;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

public class FindCommand extends Command {
    private String InputToSearch;
    public FindCommand(String input) {
        this.InputToSearch = input;
    }
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> foundTasks = taskList.findTasks(this.InputToSearch);
        if (foundTasks.size() == 0) {
            return "We could not find any matching tasks.";
        } else {
            String response = "Here are the matching tasks in your list:";
            for (Task foundTask : foundTasks) {
                    String taskString = foundTasks.indexOf(foundTask) + 1 + ". [" + foundTask.getSymbol() + "] "
                            + "[" + foundTask.getStatusIcon() + "] " + foundTask.getDescription();
                if (!(foundTask instanceof Todo)) {
                    taskString += " (" + foundTask.getDuedateString() + ")";
                }
                    response += taskString + "\n";
            }
            return response;
        }
    }

    public boolean isExit() {
        return false;
    }
}
