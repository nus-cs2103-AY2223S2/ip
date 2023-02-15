package duke.command;

import duke.task.TaskList;
import duke.UI.TextOutput;

/**
 * Displays the tasks in the current task list.
 */
public class List extends Command {
    public List(String str) {
        this.setCommandStorage(str);
    }

    @Override
    public String execute(TaskList tasks) {
        return TextOutput.makeListTaskString(tasks);
    }
}
