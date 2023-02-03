package duke.command;

import duke.*;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

public class ListCommand extends Command {

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.getNumTasks() == 0) {
            ui.showMessage("You have no tasks due!");
        } else {
            ui.showMessage("Here are the tasks you have due!");
            for (Task cur : taskList.getTasks()) {
                if (cur instanceof Todo) {
                    ui.showMessage(taskList.getTasks().indexOf(cur) + 1 + ". [" + cur.getSymbol() + "] "
                                    + "[" + cur.getStatusIcon() + "] " + cur.getDescription());
                } else {
                    ui.showMessage(taskList.getTasks().indexOf(cur) + 1 + ". [" + cur.getSymbol() + "] "
                                        + "[" + cur.getStatusIcon() + "] " + cur.getDescription()
                                        + " (" + cur.getDuedateString() + ")");
                }
            }
        }
    }

    public boolean isExit() {
        return false;
    }
}
