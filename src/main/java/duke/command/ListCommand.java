package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

public class ListCommand extends Command {

    public String execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.getNumTasks() == 0) {
            return "You have no tasks due!";
        } else {
            String response = "Here are the tasks you have due!\n";
            for (Task cur : taskList.getTasks()) {
                String taskString = taskList.getTasks().indexOf(cur) + 1 + ". [" + cur.getSymbol() + "] "
                        + "[" + cur.getStatusIcon() + "] " + cur.getDescription();
                if (!(cur instanceof Todo)) {
                    taskString +=  " (" + cur.getDuedateString() + ")";
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
