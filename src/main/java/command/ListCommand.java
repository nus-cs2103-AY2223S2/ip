package command;

import gui.Gui;
import storage.Storage;
import task.TaskList;

/**
 * Command for listing all tasks in a task list.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Gui gui, Storage storage) {
        gui.say(taskList.listItems());
    }
}
