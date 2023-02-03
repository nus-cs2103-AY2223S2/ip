package duke.command;

import duke.task.TaskList;
import duke.UI;

/**
 * Displays the tasks in the current task list.
 */
public class List extends Commands{
    public List(String str) {
        this.setCommandStorage(str);
    }
    @Override
    public void execute(TaskList tasks) {
        UI.listTasks(tasks);
    }
}
