package duke.command;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

public class FindCommand extends Command {
    private String input;
    public FindCommand(String input) throws DukeException {
        String[] inputArr = input.split(" ", 2);
        if (inputArr.length == 1 || inputArr[1].isBlank()) {
            throw new DukeException("Sorry, the description of the task cannot be empty!");
        }
        this.input = inputArr[1];
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> foundTasks = taskList.findTasks(this.input);
        if (foundTasks.size() == 0) {
            return "We could not find any matching tasks.";
        } else {
            String response = "Here are the matching tasks in your list:\n";
            for (Task task : foundTasks) {
                    String taskString = taskList.formatTaskToString(task);
                    response += taskString + "\n";
            }
            return response;
        }
    }

    public boolean isExit() {
        return false;
    }
}
