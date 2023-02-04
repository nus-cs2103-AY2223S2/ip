package command;

import gui.Gui;
import storage.Storage;
import task.Task;
import task.TaskList;

/**
 * Reminder for upcoming deadlines.
 */
public class RemindCommand extends Command {
    @Override
    public void execute(TaskList taskList, Gui gui, Storage storage) {
        gui.say(taskList.listItems(Task::isSoon));
    }
}
